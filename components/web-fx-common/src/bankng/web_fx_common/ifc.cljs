(ns bankng.web-fx-common.ifc
  (:require [com.rpl.specter :refer [path]]
            [re-frame.core :as rf]))

(def EFFECT-DB (path [:effects :db]))

(defn local-storage-effect
  [{:keys [key value]}]
  (.setItem (.-localStorage js/window) key value))

(rf/reg-fx :set-local-storage local-storage-effect)
