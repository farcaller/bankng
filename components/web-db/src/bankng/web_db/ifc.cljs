(ns bankng.web-db.ifc
  (:require [bankng.web-login.ifc.db :as login]
            [bankng.web-accounts.ifc.db :as accounts]
            [bankng.web-transfers.ifc.db :as transfers]
            [re-frame.core :as rf]))

(def default-db
  {; the current reitit route
   :current-route nil

   :login login/login-db
   :accounts accounts/accounts-db
   :transfers transfers/transfers-db})

(rf/reg-event-db
 ::initialize-db
 (fn [_ _]
   default-db))

(comment
  (-> @re-frame.db/app-db :login :jwt)
  :rcf)
