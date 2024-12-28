(ns bankng.web-accounts.ifc.subs
  (:require [bankng.web-accounts.ifc.db :refer [ACCOUNTS PREFIX]]
            [bankng.web-fx-grpc.ifc :refer [select-fetch-value]]
            [com.rpl.specter :refer [select-one]]
            [re-frame.core :as rf]))

(defn select-current-account-idx [db]
  (select-one [PREFIX :current-account-idx] db))

(rf/reg-sub
 :accounts/current-account
 (fn [db _]
   (let [accounts (select-fetch-value db ACCOUNTS)
         idx (select-current-account-idx db)]
     (if (or (empty? accounts) (nil? idx))
       nil
       (nth accounts idx)))))

(rf/reg-sub
 :accounts/current-account-idx
 (fn [db _]
   (select-current-account-idx db)))

(rf/reg-sub
 :accounts/count
 (fn [db _]
   (count (select-fetch-value db ACCOUNTS))))

(rf/reg-sub
 :accounts/accounts
 (fn [db _]
   (select-fetch-value db ACCOUNTS)))

(rf/reg-sub
 :accounts/transactions
 (fn [db [_ account-idx]]
   (select-one [ACCOUNTS :value account-idx :transactions :value] db)))
