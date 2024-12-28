(ns bankng.web-fx-grpc.ifc
  (:require [re-frame.core :as rf]
            [com.rpl.specter
             :as s
             :refer-macros [multi-transform]
             :refer [path multi-path terminal-val setval select-one]]))

(defn set-fetch
  "Sets the loading state in the db under the given `root-path`.
   
   `loading?` should be one of `true` (is loading), `false` (loaded
   successfully), or an instance of error."
  [db root-path & {:keys [loading? error value]}]
  (assert (= (count (filter some? [loading? error value])) 1)
          "Only one of loading?, error, or value should be provided.")
  (cond
    loading?
    (multi-transform [root-path (multi-path [:error (terminal-val nil)]
                                            [:loading? (terminal-val true)])] db)
    
    error
    (multi-transform [root-path (multi-path [:error (terminal-val error)]
                                            [:loading? (terminal-val false)])] db)
    
    value
    (multi-transform [root-path (multi-path [:error (terminal-val nil)]
                                            [:loading? (terminal-val false)]
                                            [:value (terminal-val value)])] db)))

(defn select-fetch
  [db root-path]
  (select-one root-path db))

(defn select-fetch-value
  [db root-path]
  (select-one [root-path :value] db))

(rf/reg-sub
 ::fetch
 (fn [db [_ root-path]]
   (select-fetch db [root-path (s/submap [:loading? :error :value])])))

(defn subscribe-fetch
  [root-path]
  (rf/subscribe [::fetch root-path]))

(def EFFECT-GRPC (path [:effects :grpc]))

(defn call-grpc
  [db params]
  (setval EFFECT-GRPC params db))
