(ns bankng.web-accounts.ifc.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :accounts/loading?
 (fn [db _]
   (-> db :accounts :loading?)))

(rf/reg-sub
 :accounts/error
 (fn [db _]
   (-> db :accounts :error)))

(rf/reg-sub
 :accounts/current-account
 (fn [db _]
   (let [accounts (-> db :accounts :accounts)
         idx (-> db :accounts :current-account-idx)]
     (if (or (empty? accounts) (nil? idx))
       nil
       (nth accounts idx)))))

(rf/reg-sub
 :accounts/current-account-idx
 (fn [db _]
   (-> db :accounts :current-account-idx)))

(rf/reg-sub
 :accounts/count
 (fn [db _]
   (count (-> db :accounts :accounts))))

(rf/reg-sub
 :accounts/accounts
 (fn [db _]
   (-> db :accounts :accounts)))
