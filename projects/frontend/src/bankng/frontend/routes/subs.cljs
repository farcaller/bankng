(ns bankng.frontend.routes.subs
  (:require [re-frame.core :as rf]
            [reitit.frontend.easy :as rfe]))

(rf/reg-sub
 :routes/current-route
 (fn [db _]
   (:current-route db)))

(rf/reg-sub
 :routes/back-route
 (fn [db _]
   (when-let [back-route-id (-> db :current-route :data :back)]
     (rfe/href back-route-id))))

(rf/reg-sub
 :routes/hide-pfp-menu
 (fn [db _]
   (-> db :current-route :data :hide-pfp)))
