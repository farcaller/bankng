(ns bankng.xtdb.ifc
  (:require [mount.core :refer [defstate]]
            [bankng.xtdb-inproc.core :as core]
            [xtdb.api :as xt]))

(defstate conn :start (core/start) :stop (core/stop conn))

(defmacro pull [query edn]
  `(xt/pull (xt/db conn) (quote ~query) ~edn))
