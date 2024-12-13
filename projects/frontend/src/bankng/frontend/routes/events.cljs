(ns bankng.frontend.routes.events
  (:require [re-frame.core :as rf]
            [reitit.frontend.easy :as rfe]
            [bankng.frontend.accounts.events :as accounts]))

(rf/reg-event-fx
 :routes/set-active-route
 (fn [{db :db} [_ match]]
   (let [db-fx {:db (assoc db :current-route match)}
         route-fx (condp = (-> match :data :name)
                    :home (accounts/on-routed db)
                    :login-2fa (when (nil? (-> db :login :full-name)) {:push-route :login})
                    nil)]
     (merge db-fx route-fx))))

(rf/reg-event-fx
 :routes/push-route
 (fn [_ [_ name]]
   {:push-route name}))

(rf/reg-fx :push-route rfe/push-state)

