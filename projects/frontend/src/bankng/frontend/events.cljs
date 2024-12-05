(ns bankng.frontend.events
  (:require [bankng.frontend.db :as db]
            [re-frame.core :as rf]))

(rf/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(when goog.DEBUG
  (rf/reg-event-db
   ::reset-db
   (fn [_ [_ d]]
     d))
  (rf/reg-event-db
   ::reset-route
   (fn [db [_ name]]
     (assoc db :current-route (reitit.frontend/match-by-name! (reitit.frontend/router bankng.frontend.routes/routes) name)))))

(comment
  re-frame.db/app-db
  (rf/dispatch-sync [::reset-route :login])
  :rcf)
