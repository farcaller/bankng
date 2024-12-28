(ns bankng.web-routes.ifc.router
  (:require [re-frame.core :as rf]
            [reitit.frontend.easy :as rfe]
            [reitit.frontend :as reitit]
            [bankng.web-login.ifc.views :as login]
            [bankng.web-accounts.ifc.views :as accounts]
            [bankng.web-transfers.ifc.views :as transfers]
            [bankng.web-common.ifc :refer [chrome]]))

(def routes
  [["" {:auth :required}
    ["/"
     {:name :home}]
    ["/accounts"
     {:back :home}
     [""
      {:name :accounts-list
       :back :home}]
     ["/:account-id"
      {:name :account-info
       :back :home}]]
    ["/send"
     {:name :send
      :back :home}]]
   ["/login"
    {:hide-pfp true}
    ["" {:name :login}]
    ["/2fa"
     {:name :login-2fa
      :auth :required-login}]]])

(defn get-view [route-name]
  (route-name
   {:home accounts/account-card
    :accounts-list accounts/accounts-list
    :account-info accounts/account-details
    :send transfers/create-transfer
    :login login/login-name
    :login-2fa login/login-otp}))

(defn start-router! []
  (rfe/start!
   (reitit/router routes)
   (fn [match _history]
     (rf/dispatch [:routes/set-active-route match]))
   {:use-fragment false}))

(defn router-component []
  (when-let [current-route @(rf/subscribe [:routes/current-route])]
    [chrome (-> current-route :data :name get-view)]))

(comment
  (start-router!)
  (-> @re-frame.db/app-db :current-route :path-params :account-id)
  (reitit/match-by-name! (reitit/router routes) :account-info {:account-id "123"})
  (rf/dispatch [:routes/push-route :login-2fa])
  (rfe/href :account-info {:account-id "123"})
  :rcf)
