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
     :view login/login-page}]])

(rf/reg-event-db
 ::set-active-route
 (fn [db [_ match]]
   (assoc db :current-route match)))

(defn navigate! [match _]
  (rf/dispatch [::set-active-route match]))

(rf/reg-sub
 ::current-route
 (fn [db _]
   (:current-route db)))

(defn start-router! []
  (rfe/start!
   (reitit/router routes)
   navigate!
   {:use-fragment false}))

(defn router-component []
  (let [current-route @(rf/subscribe [::current-route])]
    [(-> current-route :data :view)]))

(comment
  (reitit/match-by-name! (reitit/router routes) :home)
  :rcf)
