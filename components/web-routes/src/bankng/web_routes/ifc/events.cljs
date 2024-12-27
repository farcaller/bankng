(ns bankng.web-routes.ifc.events
  (:require [bankng.web-accounts.ifc.events :as accounts]
            [bankng.web-transfers.ifc.events :as transfers]
            [re-frame.core :as rf]
            [reitit.frontend.easy :as rfe]))

(def verify-auth
  (re-frame.core/->interceptor
   :id :verify-auth
   :after
   (fn [context]
     (let [db (-> context :effects :db)
                   route-match (:current-route db)
                   auth (-> route-match :data :auth)]
               (if-let [new-ctx (condp = auth
                                  :required (when (nil? (-> db :login :jwt))
                                              (assoc-in context [:effects :push-route] :login))
                                  :required-login (when (nil? (-> db :login :full-name))
                                                    (println "redirecting to login, no name" (:login db))
                                                    (assoc-in context [:effects :push-route] :login))
                                  nil)]
                 new-ctx
                 context)))))

(rf/reg-event-db
 :routes/set-active-route
 [verify-auth accounts/fetch-accounts transfers/reset-source-account]
 (fn [db [_ match]]
   (-> db
    (assoc :previous-route (:current-route db))
    (assoc :current-route match))))

(rf/reg-event-fx
 :routes/push-route
 (fn [_ [_ name]]
   {:push-route name}))

(rf/reg-fx :push-route rfe/push-state)
