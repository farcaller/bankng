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

(rf/reg-sub
 :login/first-name
 (fn [db _]
   (-> db :login :first-name)))

(rf/reg-sub
 :login/pfp-url
 (fn [db _]
   (-> db :login :pfp-url)))

(rf/reg-sub
 :login/full-name
 (fn [db _]
   (-> db :login :full-name)))
