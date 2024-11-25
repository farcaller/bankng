(ns net.dracones.bankng.frontend.login.events
  (:require [re-frame.core :as rf]
            [kitchen-async.promise :as p]
            [net.dracones.bankng.pb-frontend.interface :as fpb]
            [clojure.string :as str]))

(rf/reg-event-fx
 :login/send-otp-code
 (fn [{db :db} [_ full-name]]
   (let [full-name (str/trim full-name)]
     (if (str/includes? full-name " ")
       {:grpc {:full-name full-name}
        :db
        (-> db
            (assoc-in [:login :error] nil)
            (assoc-in [:login :full-name] full-name)
            (assoc-in [:login :loading?] true))}
       {:db
        (-> db
            (assoc-in [:login :error] (js/Error. "Please enter the full name.")))}))))

(rf/reg-event-db
 :login/otp-finalise-error
 (fn [db [_ error]]
   (-> db
       (assoc-in [:login :error] error)
       (assoc-in [:login :loading?] false))))

(defn grpc-effect
  [request]
  (-> (fpb/first-factor (:full-name request))
      (p/then #(rf/dispatch [:login/perform-otp (:firstName %) (:pfpUrl %)]))
      (p/catch* #(rf/dispatch [:login/otp-finalise-error %]))))

(rf/reg-fx :grpc grpc-effect)
