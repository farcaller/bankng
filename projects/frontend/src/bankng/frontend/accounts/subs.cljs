(ns bankng.frontend.accounts.subs
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
   (nth (-> db :accounts :accounts) (-> db :accounts :current-account-idx))))

(rf/reg-sub
 :accounts/current-account-idx
 (fn [db _]
   (-> db :accounts :current-account-idx)))

(rf/reg-sub
 :accounts/count
 (fn [db _]
   (count (-> db :accounts :accounts))))