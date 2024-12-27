(ns bankng.frontend.db
  (:require [bankng.web-login.ifc.db :as login]
            [bankng.frontend.accounts.db :as accounts]
            [bankng.frontend.transfers.db :as transfers]))

(def default-db
  {; the current reitit route
   :current-route nil

   :login login/login-db
   :accounts accounts/accounts-db
   :transfers transfers/transfers-db})
