(ns bankng.config.interface
  (:require [bankng.config.core :as core]
            [mount.core :refer [defstate]]))

(defstate config :start (core/load-config))
