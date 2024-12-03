(ns net.dracones.bankng.jwt.core
  (:require [buddy.sign.jwt :as jwt]
            [buddy.core.keys :as keys]
            [mount.core :refer [defstate]]
            [net.dracones.bankng.config.interface :refer [config]])
  (:import [java.time Instant ZoneId ZonedDateTime]
           [java.time.temporal ChronoUnit]))

(defstate iss :start (-> config :jwt :issuer))
(defstate aud :start (-> config :jwt :audience))
(defstate ttl :start (-> config :jwt :ttl))
(defstate private-key :start (-> config :jwt :private-key keys/private-key))

(defn create-jwt [char-id]
  (let [now (ZonedDateTime/now (ZoneId/systemDefault))
        claim {:iss iss
               :sub char-id
               :aud aud
               :iat (.toEpochSecond now)
               :exp (.toEpochSecond (.plus now ttl ChronoUnit/HOURS))}]
    (jwt/sign claim private-key {:alg :es256})))

(comment
  (create-jwt "123")
  :rcf)