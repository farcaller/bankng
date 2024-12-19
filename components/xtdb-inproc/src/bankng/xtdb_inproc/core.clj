(ns bankng.xtdb-inproc.core
  (:require [xtdb.api :as xt])
  (:import [xtdb.node XtdbNode]))

(defn start-xtdb! []
  (xt/start-node {}))

(defn start []
  (start-xtdb!))

(defn stop [^XtdbNode conn]
  (.close conn))
