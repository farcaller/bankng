(ns bankng.frontend.routes.router
  (:require [re-frame.core :as rf]
            [reitit.frontend.easy :as rfe]
            [reitit.frontend :as reitit]
            [bankng.frontend.login.views :as login]
            [bankng.frontend.accounts.views :as accounts]))

(def routes
  [["/"
    {:name :home
     :view accounts/account-page}]
   ["/login"
    {:name :login
     :view login/login-page}]
   ["/login-2fa"
    {:name :login-2fa
     :view login/login-otp-page}]])

(defn start-router! []
  (rfe/start!
   (reitit/router routes)
   #(rf/dispatch [:routes/set-active-route %])
   {:use-fragment false}))

(defn router-component []
  (let [current-route @(rf/subscribe [:routes/current-route])]
    [(-> current-route :data :view)]))

(comment
  (reitit/match-by-name! (reitit/router routes) :home)
  (rf/dispatch [:routes/push-route :login-2fa])
  :rcf)
