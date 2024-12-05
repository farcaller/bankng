(ns bankng.redis.core
  (:require [mount.core :refer [defstate]]
            [bankng.config.ifc :refer [config]]
            [taoensso.carmine :as car :refer [wcar]]))

(defstate connection-pool
  :start (car/connection-pool {})
  :stop (.close ^java.io.Closeable connection-pool))

(defstate connection-spec :start {:uri (-> config :redis :uri)})

(defstate wcar-opts
  :start {:pool connection-pool :spec connection-spec})

(defmacro wcar* [& body] `(car/wcar wcar-opts ~@body))

(defn store [key value ttl]
  (wcar* (car/setex key ttl value)))

(defn get [key]
  (wcar* (car/get key)))

(defn del [key]
  (wcar* (car/del key)))

(defn dump-kvs []
  (let [keys (wcar* (car/keys "*"))
        vals (map #(wcar* (car/get %)) keys)
        ttls (map #(wcar* (car/ttl %)) keys)
        m (map #(hash-map :key %1 :val %2 :ttl %3) keys vals ttls)]
    (tap> (with-meta m {:portal.viewer/default :portal.viewer/table}))
    m)
  )

(comment
  (dump-kvs)
  :rcf)