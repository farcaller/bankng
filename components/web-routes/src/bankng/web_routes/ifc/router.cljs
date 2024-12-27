(ns bankng.web-routes.ifc.router
  (:require [re-frame.core :as rf]
            [reitit.frontend.easy :as rfe]
            [reitit.frontend :as reitit]
            [bankng.web-login.ifc.views :as login]
            [bankng.frontend.accounts.views :as accounts]
            [bankng.frontend.transfers.views :as transfers]
            [bankng.web-common.ifc :refer [chrome]]))

(def routes
  [["" {:auth :required}
    ["/"
     {:name :home
      :view #'accounts/account-card}]
    ["/accounts"
     {:back :home}
     [""
      {:name :accounts-list
       :view #'accounts/accounts-list
       :back :home}]
     ["/:account-id"
      {:name :account-info
       :view #'accounts/account-details
       :back :home}]]
    ["/send"
     {:name :send
      :view #'transfers/create-transfer
      :back :home}]]
   ["/login"
    {:hide-pfp true}
    ["" {:name :login
         :view #'login/login-name}]
    ["/2fa"
     {:name :login-2fa
      :view #'login/login-otp
      :auth :required-login}]]])

(defn start-router! []
  (rfe/start!
   (reitit/router routes)
   (fn [match _history]
     (rf/dispatch [:routes/set-active-route match]))
   {:use-fragment false}))

(defn router-component []
  (when-let [current-route @(rf/subscribe [:routes/current-route])]
    [chrome @(-> current-route :data :view)]))

(comment
  (start-router!)
  (-> @re-frame.db/app-db :current-route :path-params :account-id)
  (reitit/match-by-name! (reitit/router routes) :account-info {:account-id "123"})
  (rf/dispatch [:routes/push-route :login-2fa])
  (rfe/href :account-info {:account-id "123"})
  :rcf)
