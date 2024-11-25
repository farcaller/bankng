(ns net.dracones.bankng.frontend.login.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :login/loading?
 (fn [db _]
   (-> db :login :loading?)))

(rf/reg-sub
 :login/error
 (fn [db _]
   (-> db :login :error)))
