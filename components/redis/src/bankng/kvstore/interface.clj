(ns bankng.kvstore.interface
  (:require [bankng.redis.core :as redis]))

(defn store [key value ttl]
  (redis/store key value ttl))

(defn get [key]
  (redis/get key))

(defn del [key]
  (redis/del key))
