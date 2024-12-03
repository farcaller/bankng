(ns net.dracones.bankng.kvstore.interface
  (:require [net.dracones.bankng.redis.core :as redis]))

(defn store [key value ttl]
  (redis/store key value ttl))

(defn get [key]
  (redis/get key))

(defn del [key]
  (redis/del key))
