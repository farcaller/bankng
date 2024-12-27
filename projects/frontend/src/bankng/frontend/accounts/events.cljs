(ns bankng.frontend.accounts.events
  (:require [re-frame.core :as rf]
            ["grpc-web" :refer [StatusCode]]
            [bankng.pb-frontend.ifc :as fpb]))

(defn db->loading [db]
  (-> db
      (assoc-in [:accounts :error] nil)
      (assoc-in [:accounts :loading?] true)))

(defn db->loading-complete [db]
  (-> db
      (assoc-in [:accounts :error] nil)
      (assoc-in [:accounts :loading?] false)))

(defn db->failed [db err]
  (-> db
      (assoc-in [:accounts :error] err)
      (assoc-in [:accounts :loading?] false)))

(rf/reg-event-db
 :accounts/accounts-finalise-error
 (fn [db [_ error]]
   (let [error (condp = (.-code error)
                 StatusCode.NOT_FOUND (js/Error. "account not found.")
                 error)]
     (db->failed db error))))

(defn init-account [apb]
  {:iban (:iban apb)
   :name (:name apb)
   :currency (:currency apb)
   :currency-code (:currencyCode apb)
   :balance (:balance apb)
   :transactions []
   :transactions-loading? false})

(rf/reg-event-fx
 :accounts/accounts-finalise-fetch
 (fn [{db :db} [_ {:keys [accountsList]}]]
   {:db (-> db
            (db->loading-complete)
            (assoc-in [:accounts :accounts] (into [] (map init-account accountsList))))
    :dispatch [:accounts/set-active-account-idx 0]}))

(def fetch-transactions
  (re-frame.core/->interceptor
   :id :fetch-transactions
   :after
   (fn [context]
     (let [db (-> context :effects :db)
           accounts (-> db :accounts)
           account-idx (-> accounts :current-account-idx)
           account (-> accounts :accounts (nth account-idx))
           account-iban (-> account :iban)
           txns (-> account :transactions)]
       (if (and (empty? txns) (not (-> account :transactions-loading?)))
         (-> context
             (assoc-in [:effects :db :accounts account-idx :transactions-loading?] true)
             (assoc-in [:effects :grpc] {:request fpb/list-transactions
                                         :args [(-> db :login :jwt) account-iban]
                                         :on-success :accounts/transactions-finalise-fetch
                                         :on-error :accounts/transactions-finalise-error
                                         :context account-idx}))
         context)))))

(rf/reg-event-db
 :accounts/set-active-account-idx
 [fetch-transactions]
 (fn [db [_ idx]]
   (assoc-in db [:accounts :current-account-idx] idx)))

(def fetch-accounts
  (re-frame.core/->interceptor
   :id :fetch-accounts
   :after
   (fn [context]
     (let [db (-> context :effects :db)
           route-name (-> db :current-route :data :name)]
       (if (and (= route-name :home)
                (nil? (-> db :accounts :accounts))
                (some? (-> db :login :jwt)))
         (assoc-in context [:effects :grpc] {:request fpb/list-accounts
                                             :args [(-> db :login :jwt)]
                                             :on-success :accounts/accounts-finalise-fetch
                                             :on-error :accounts/accounts-finalise-error})
         context)))))

(rf/reg-event-db
 :accounts/transactions-finalise-fetch
 (fn [db [_ {:keys [transactionsList]} account-idx]]
   (-> db
       (assoc-in [:accounts :accounts account-idx :transactions] transactionsList)
       (assoc-in [:accounts :accounts account-idx :transactions-loading?] false))))

(rf/reg-event-db
 :accounts/transactions-finalise-error
 (fn [db [_ err]]
   ; TODO: implement 
   (.error js/console err)
   db))
