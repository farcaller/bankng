(ns bankng.frontend.routes.router
  (:require [re-frame.core :as rf]
            [reitit.frontend.easy :as rfe]
            [reitit.frontend :as reitit]
            [bankng.frontend.login.views :as login]
            [bankng.frontend.accounts.views :as accounts]
            [bankng.frontend.common.views :refer [chrome]]))

(def routes
  [["/"
    {:name :home
     :view accounts/account-card
     :auth :required}]
   ["/accounts/:account-id"
    {:name :account-info
     :view accounts/account-details
     :auth :required
     :back :home}]
   ["/login"
    {:name :login
     :view login/login-name}]
   ["/login-2fa"
    {:name :login-2fa
     :view login/login-otp
     :auth :required-login
     :hide-pfp true}]])

(defn start-router! []
  (rfe/start!
   (reitit/router routes)
   (fn [match _history]
     (rf/dispatch [:routes/set-active-route match]))
   {:use-fragment false}))

(defn router-component []
  (when-let [current-route @(rf/subscribe [:routes/current-route])]
    [chrome (-> current-route :data :view)]))

(comment
  (start-router!)
  (-> @re-frame.db/app-db :current-route :path-params :account-id)
  (reitit/match-by-name! (reitit/router routes) :home)
  (rf/dispatch [:routes/push-route :login-2fa])
  (rfe/href :account-info {:account-id "123"})
  :rcf)
