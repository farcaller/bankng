(ns bankng.web-accounts.ifc.db)

(def accounts-db
  {:loading? false
   :error nil
   
   :current-account-idx 0
   :accounts nil
   
   :account-details-iban nil})
