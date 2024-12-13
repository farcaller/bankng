(ns bankng.frontend.routes.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :routes/current-route
 (fn [db _]
   (:current-route db)))
