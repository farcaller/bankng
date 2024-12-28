(ns bankng.web-accounts.ifc.db
  (:require [com.rpl.specter :refer [path]]))

(def accounts-db
  {:loading? false
   :error nil
   
   :current-account-idx nil
   :accounts nil
   
   :account-details-iban nil})

(def PREFIX (path :accounts))

(def ACCOUNTS (path [PREFIX :accounts]))
