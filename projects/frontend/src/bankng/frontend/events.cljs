(ns bankng.frontend.events
  (:require [bankng.frontend.db :as db]
            [re-frame.core :as rf]))

(rf/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(comment
  (-> @re-frame.db/app-db :login :jwt)
  :rcf)
