(ns bankng.config.core
  (:require [cprop.core :as cprop]))

(defn load-config []
  (cprop/load-config))
