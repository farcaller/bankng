(ns net.dracones.bankng.xtdb-inproc.core
  (:require [xtdb.api :as xt]))

(defn start-xtdb! []
  (xt/start-node {}))

(defn start []
  (start-xtdb!))

(defn stop [conn]
  (.close conn))
