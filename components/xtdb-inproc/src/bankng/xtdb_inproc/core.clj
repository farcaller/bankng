(ns bankng.xtdb-inproc.core
  (:require [xtdb.api :as xt])
  (:import [java.lang AutoCloseable]))

(defn start-xtdb! []
  (xt/start-node {}))

(defn start []
  (start-xtdb!))

(defn stop [^AutoCloseable conn]
  (.close conn))
