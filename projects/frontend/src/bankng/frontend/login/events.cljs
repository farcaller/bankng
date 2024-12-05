(ns bankng.frontend.login.events
  (:require [re-frame.core :as rf]
            [kitchen-async.promise :as p]
            [bankng.pb-frontend.interface :as fpb]
            [clojure.string :as str]
            ["grpc-web" :refer [StatusCode]]))

(defn db->loading [db]
  (-> db
      (assoc-in [:login :error] nil)
      (assoc-in [:login :loading?] true)))

(defn db->loading-complete [db]
  (-> db
      (assoc-in [:login :error] nil)
      (assoc-in [:login :loading?] false)))

(defn db->failed [db err]
  (-> db
      (assoc-in [:login :error] err)
      (assoc-in [:login :loading?] false)))

(rf/reg-event-fx
 :login/send-otp-code
 (fn [{db :db} [_ full-name]]
   (let [full-name (str/trim full-name)]
     (if (str/includes? full-name " ")
       {:grpc {:request fpb/first-factor
               :args [full-name]
               :on-success :login/otp-finalise-success
               :on-error :login/otp-finalise-error}
        :db (-> db
                (db->loading)
                (assoc-in [:login :full-name] full-name))}
       {:db (db->failed db (js/Error. "Please enter the full name."))}))))

(rf/reg-event-fx
 :login/verify-otp-code
 (fn [{db :db} [_ code]]
   (if (= 4 (count code))
     {:grpc {:request fpb/second-factor
             :args [(-> db :login :session-id) code]
             :on-success :login/otp-verify-success
             :on-error :login/otp-verify-error}
      :db (db->loading db)}
     {:db (db->failed db (js/Error. "Please enter the code."))})))

(rf/reg-event-db
 :login/otp-finalise-error
 (fn [db [_ error]]
   (let [error (condp = (.-code error)
                 StatusCode.NOT_FOUND (js/Error. "account not found.")
                 error)]
     (db->failed db error))))

(rf/reg-event-fx
 :login/otp-finalise-success
 (fn [{db :db} [_ {:keys [firstName pfpUrl sessionId]}]]
   {:db (-> db
            (db->loading-complete)
            (assoc-in [:login :first-name] firstName)
            (assoc-in [:login :pfp-url] pfpUrl)
            (assoc-in [:login :session-id] sessionId))
    :push-route :login-2fa}))

(rf/reg-event-fx
 :login/otp-verify-error
 (fn [{db :db} [_ error]]
   (let [no-session? (= (.-code error) StatusCode.NOT_FOUND)
         bad-code? (= (.-code error) StatusCode.INVALID_ARGUMENT)
         error (if (or no-session? bad-code?) error (js/Error. "failed to verify the code"))
         fxs {:db (db->failed db error)}
         fxs (if no-session?
               (-> fxs
                   (assoc :push-route :login)
                   (assoc-in [:db :login :first-name] nil)
                   (assoc-in [:db :login :pfp-url] nil)
                   (assoc-in [:db :login :session-id] nil))
               fxs)]
     fxs)))

(rf/reg-event-fx
 :login/otp-verify-success
 (fn [{db :db} [_ {:keys [jwt]}]]
   {:db (-> db
            (db->loading-complete)
            (assoc-in [:login :jwt] jwt))
    :push-route :home}))

(defn grpc-effect
  [{:keys [request args on-success on-error]}]
  (-> (apply request args)
      (p/then #(rf/dispatch [on-success %]))
      (p/catch* #(rf/dispatch [on-error %]))))

(rf/reg-fx :grpc grpc-effect)

(comment
  StatusCode.INVALID_ARGUMENT
  :rcf)
