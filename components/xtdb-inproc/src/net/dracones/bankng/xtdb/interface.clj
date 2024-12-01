(ns net.dracones.bankng.xtdb.interface
  (:require [mount.core :refer [defstate]]
            [net.dracones.bankng.xtdb-inproc.core :as core]))

(defstate conn :start (core/start) :stop (core/stop conn))
