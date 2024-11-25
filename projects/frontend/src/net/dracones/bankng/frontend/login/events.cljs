(ns net.dracones.bankng.frontend.login.events
  (:require [re-frame.core :as rf]))

(rf/reg-event-fx
 :login/send-otp-code
 (fn [{db :db} [_ full-name]]
   {:grpc {:full-name full-name}
    :db
    (-> db
        (assoc-in [:login :full-name] full-name)
        (assoc-in [:login :loading?] true))}))
