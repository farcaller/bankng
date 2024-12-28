(ns bankng.web-accounts.ifc.events
  (:require ["grpc-web" :refer [StatusCode]]
            [bankng.pb-frontend.ifc :as fpb]
            [bankng.web-accounts.ifc.db :refer [ACCOUNTS PREFIX]]
            [bankng.web-fx-common.ifc :refer [EFFECT-DB]]
            [bankng.web-fx-grpc.ifc :refer [call-grpc select-fetch set-fetch]]
            [com.rpl.specter :refer [select-one setval]]
            [re-frame.core :as rf]))

(defn init-account [apb]
  {:iban (:iban apb)
   :name (:name apb)
   :currency (:currency apb)
   :currency-code (:currencyCode apb)
   :balance (:balance apb)
   :transactions nil})

(rf/reg-event-fx
 ::accounts-fetch-success
 (fn [{db :db} [_ {:keys [accountsList]}]]
   {:db (set-fetch db ACCOUNTS :value (mapv init-account accountsList))
    :dispatch [:accounts/set-active-account-idx 0]}))

(rf/reg-event-db
 ::accounts-fetch-error
 (fn [db [_ error]]
   (let [error (condp = (.-code error)
                 StatusCode.NOT_FOUND (js/Error. "account not found.")
                 error)]
     (set-fetch db ACCOUNTS :error error))))

(def fetch-transactions
  (re-frame.core/->interceptor
   :id :fetch-transactions
   :after
   (fn [context]
     (let [db (select-one EFFECT-DB context)
           accounts (select-one PREFIX db)
           account-idx (-> accounts :current-account-idx)
           account (select-one [:value account-idx] (select-fetch db ACCOUNTS))
           {:keys [iban] {transactions :value} :transactions} account]
       (if (and (empty? transactions) (not (-> account :transactions-loading?)))
         (-> context
             (set-fetch [EFFECT-DB ACCOUNTS :value account-idx :transactions] :loading? true)
             (call-grpc {:request fpb/list-transactions
                         :args [(-> context :effects :db :login :jwt) iban] ; TODO: use login selector
                         :on-success ::transactions-fetch-success
                         :on-error ::transactions-fetch-error
                         :context account-idx}))
         context)))))

(rf/reg-event-db
 :accounts/set-active-account-idx
 [fetch-transactions]
 (fn [db [_ idx]]
   (setval [PREFIX :current-account-idx] idx db)))

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
         (-> context
             (set-fetch [EFFECT-DB ACCOUNTS] :loading? true)
             (call-grpc {:request fpb/list-accounts
                         :args [(-> db :login :jwt)]
                         :on-success ::accounts-fetch-success
                         :on-error ::accounts-fetch-error}))
         context)))))

(rf/reg-event-db
 ::transactions-fetch-success
 (fn [db [_ {:keys [transactionsList]} account-idx]]
   (set-fetch db [ACCOUNTS :value account-idx :transactions] :value transactionsList)))

(rf/reg-event-db
 ::transactions-fetch-error
 (fn [db [_ err account-idx]]
   ; TODO: implement
   (set-fetch db [ACCOUNTS :value account-idx :transactions] :error err)
   (.error js/console err)
   db))
