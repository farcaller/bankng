(ns net.dracones.bankng.config.interface
  (:require [net.dracones.bankng.config.core :as core]
            [mount.core :refer [defstate]]))

(defstate config :start (core/load-config))
