(ns bankng.xtdb.ifc
  (:require [mount.core :refer [defstate]]
            [bankng.xtdb-inproc.core :as core]))

(defstate conn :start (core/start) :stop (core/stop conn))