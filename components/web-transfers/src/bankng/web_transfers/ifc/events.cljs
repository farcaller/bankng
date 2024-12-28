(ns bankng.web-transfers.ifc.events
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
 :transfers/set-recipient-type
 (fn [db [_ recipient-type]]
   (assoc-in db [:transfers :recipient-type] recipient-type)))

(rf/reg-event-db
 :transfers/set-source-account
 (fn [db [_ iban]]
   (let [accounts (-> db :accounts :accounts :value)
         account (first (filter #(= iban (:iban %)) accounts))]
     (assoc-in db [:transfers :source-account] account))))

(def reset-source-account
  (re-frame.core/->interceptor
   :id :reset-source-account
   :after
   (fn [context]
     (let [db (-> context :effects :db)
           route-name (-> db :current-route :data :name)
           idx (-> db :accounts :current-account-idx)]
       (if (= route-name :send)
         (assoc-in context [:effects :db :transfers :source-account] (when idx (-> db :accounts :accounts :value (nth idx))))
         context)))))
