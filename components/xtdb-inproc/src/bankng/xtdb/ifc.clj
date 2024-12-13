(ns bankng.xtdb.ifc
  (:require [mount.core :refer [defstate]]
            [bankng.xtdb-inproc.core :as core]
            [xtdb.api :as xt]
            [taoensso.telemere :refer [trace!]]))

(defstate conn :start (core/start) :stop (core/stop conn))

(defmacro pull [query eid]
  `(trace!
    {:id :xtdb/pull
     :otel/trace-attrs {:query (quote ~query) :eid ~eid}}
    (xt/pull (xt/db conn) (quote ~query) ~eid)))
