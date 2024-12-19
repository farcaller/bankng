(ns bankng.frontend.transfers.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :transfers/recipient-type
 (fn [db _]
   (-> db :transfers :recipient-type)))

(rf/reg-sub
 :transfers/source-account
 (fn [db _]
   (-> db :transfers :source-account)))
