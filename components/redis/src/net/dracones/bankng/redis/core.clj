(ns net.dracones.bankng.redis.core
  (:require [mount.core :refer [defstate]]
            [taoensso.carmine :as car :refer [wcar]]))

(defstate connection-pool
  :start (car/connection-pool {})
  :stop (.close connection-pool))

(def connection-spec {:uri "redis://localhost:6379/"})

(defstate wcar-opts
  :start {:pool connection-pool :spec connection-spec})

(defmacro wcar* [& body] `(car/wcar wcar-opts ~@body))

(defn store [key value ttl]
  (wcar* (car/setex key ttl value)))

(defn get [key]
  (wcar* (car/get key)))

(defn dump-kvs []
  (let [keys (wcar* (car/keys "*"))
        vals (map #(wcar* (car/get %)) keys)
        m (zipmap keys vals)]
    (tap> (with-meta m {:portal.viewer/default :portal.viewer/table}))
    m)
  )

(comment
  (dump-kvs)
  :rcf)
