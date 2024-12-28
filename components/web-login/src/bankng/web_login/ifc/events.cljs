(ns bankng.web-login.ifc.events
  (:require ["grpc-web" :refer [StatusCode]]
            [bankng.pb-frontend.ifc :as fpb]
            [bankng.web-fx-common.ifc :refer [EFFECT-DB]]
            [bankng.web-fx-grpc.ifc :refer [call-grpc set-fetch]]
            [bankng.web-login.ifc.db :refer [PREFIX]]
            [clojure.string :as str]
            [com.rpl.specter
             :refer-macros [setval select-one multi-transform]
             :refer [terminal-val multi-path]]
            [kitchen-async.promise :as p]
            [re-frame.core :as rf]))

(rf/reg-event-fx
 :login/send-otp-code
 (fn [{db :db} [_ full-name]]
   (let [full-name (str/trim full-name)]
     (if (str/includes? full-name " ")
       {:db (-> db
                (set-fetch PREFIX :loading? true)
                (->> (setval [PREFIX :full-name] full-name)))
        :grpc {:request fpb/first-factor
               :args [full-name]
               :on-success ::otp-fetch-success
               :on-error ::otp-fetch-error}}
       {:db (set-fetch db PREFIX :error (js/Error. "Please enter the full name."))}))))

(rf/reg-event-fx
 :login/verify-otp-code
 (fn [{db :db} [_ code]]
   (if (= 4 (count code))
     {:db (set-fetch db PREFIX :loading? true)
      :grpc {:request fpb/second-factor
             :args [(select-one [PREFIX :session-id] db) code]
             :on-success ::otp-verify-success
             :on-error ::otp-verify-error}}
     {:db (set-fetch [EFFECT-DB PREFIX] :error (js/Error. "Please enter the code."))})))

(rf/reg-event-db
 ::otp-fetch-error
 (fn [db [_ error]]
   (let [error (condp = (.-code error)
                 StatusCode.NOT_FOUND (js/Error. "account not found.")
                 error)]
     (set-fetch db PREFIX :error error))))

(defn set-session-info
  [db prefix full-name first-name pfp-url session-id]
  (multi-transform [prefix (multi-path [:full-name (terminal-val full-name)]
                                       [:first-name (terminal-val first-name)]
                                       [:pfp-url (terminal-val pfp-url)]
                                       [:session-id (terminal-val session-id)])] db))

(rf/reg-event-fx
 ::otp-fetch-success
 (fn [{db :db} [_ {:keys [fullName firstName pfpUrl sessionId]}]]
   {:db (-> db
            (set-fetch PREFIX :value true)
            (set-session-info PREFIX fullName firstName pfpUrl sessionId))
    :push-route :login-2fa}))

(rf/reg-event-fx
 ::otp-verify-error
 (fn [{db :db} [_ error]]
   (let [no-session? (= (.-code error) StatusCode.NOT_FOUND)
         bad-code? (= (.-code error) StatusCode.INVALID_ARGUMENT)
         error (if (or no-session? bad-code?) error (js/Error. "Failed to verify the code."))
         fxs {:db (set-fetch db PREFIX :error error)}
         fxs (if no-session?
               (-> fxs
                   (assoc :push-route :login)
                   (set-session-info [:db PREFIX] nil nil nil nil))
               fxs)]
     fxs)))

(rf/reg-event-fx
 ::otp-verify-success
 (fn [{db :db} [_ {:keys [jwt]}]]
   {:db (-> db
            (set-fetch [PREFIX] :value true)
            (assoc-in [:login :jwt] jwt))
    :set-local-storage {:key "full-name" :value (-> db :login :full-name)}
    :push-route :home}))

(defn grpc-effect
  [{:keys [request args on-success on-error context]}]
  (-> (apply request args)
      (p/then #(rf/dispatch [on-success % context]))
      (p/catch* #(rf/dispatch [on-error % context]))))

(rf/reg-fx :grpc grpc-effect)

(defn local-storage-effect
  [{:keys [key value]}]
  (.setItem (.-localStorage js/window) key value))

(rf/reg-fx :set-local-storage local-storage-effect)

(comment
  StatusCode.INVALID_ARGUMENT
  :rcf)
