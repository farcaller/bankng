(ns net.dracones.bankng.frontend.routes
  (:require [re-frame.core :as rf]
            [reitit.frontend.easy :as rfe]
            [reitit.frontend :as reitit]
            [reitit.frontend.controllers :as rfc]
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
     :view login/login-otp-page
     :controllers [{:start (fn [& params]
                             (when-not @(rf/subscribe [:login/full-name])
                               (rf/dispatch [::push-route :login])))}]}]])

(rf/reg-event-db
 ::set-active-route
 (fn [db [_ match]]
   (assoc db :current-route match)))

(defn navigate! [new-match _]
  (let [old-match (rf/subscribe [::current-route])]
    (println "navigate" old-match " -- " new-match)
    (when new-match
      (let [cs (rfc/apply-controllers (:controllers @old-match) new-match)
            m  (assoc new-match :controllers cs)]
        (rf/dispatch [::set-active-route m])))))

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

(rf/reg-fx :push-route rfe/push-state)

(rf/reg-event-fx
 ::push-route
 (fn [_ [_ name]]
   {:push-route name}))

(comment
  (reitit/match-by-name! (reitit/router routes) :home)
  (rf/dispatch [::push-route :login-2fa])
  :rcf)
