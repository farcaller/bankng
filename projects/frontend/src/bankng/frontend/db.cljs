(ns bankng.frontend.db
  (:require [bankng.frontend.login.db :as login]
            [bankng.frontend.accounts.db :as accounts]))

(def default-db
  {; the current reitit route
   :current-route nil

   :login login/login-db
   :accounts accounts/accounts-db})
