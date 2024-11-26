(ns net.dracones.bankng.frontend.routes
  (:require [re-frame.core :as rf]
            [reitit.frontend.easy :as rfe]
            [reitit.frontend :as reitit]
            [net.dracones.bankng.frontend.login.views :as login]))

(def routes
  [["/"
    {:name :home
     :view (fn [] [:div "Redirecting..."])}]
   ["/login"
    {:name :login
     :view login/login-page}]
   ["/login-2fa"
    {:name :login-2fa
     :view login/login-otp-page}]])

(rf/reg-event-fx
 ::set-active-route
 (fn [{db :db} [_ match]]
   (let [db-fx {:db (assoc db :current-route match)}
         route-fx (condp = (-> match :data :name)
                    :login-2fa (when (nil? (-> db :login :full-name))
                                 {:push-route :login})
                    nil)]
     (merge db-fx route-fx))))

(rf/reg-event-fx
 ::push-route
 (fn [_ [_ name]]
   {:push-route name}))

(rf/reg-fx :push-route rfe/push-state)

(rf/reg-sub
 ::current-route
 (fn [db _]
   (:current-route db)))

(defn start-router! []
  (rfe/start!
   (reitit/router routes)
   #(rf/dispatch [::set-active-route %])
   {:use-fragment false}))

(defn router-component []
  (let [current-route @(rf/subscribe [::current-route])]
    [(-> current-route :data :view)]))

(comment
  (reitit/match-by-name! (reitit/router routes) :home)
  (rf/dispatch [::push-route :login-2fa])
  :rcf)
