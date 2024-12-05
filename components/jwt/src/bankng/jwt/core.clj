(ns bankng.jwt.core
  (:require [buddy.sign.jwt :as jwt]
            [buddy.core.keys :as keys]
            [mount.core :refer [defstate]]
            [bankng.config.interface :refer [config]])
  (:import [java.time ZoneId ZonedDateTime]
           [java.time.temporal ChronoUnit]))

(defstate iss :start (-> config :jwt :issuer))
(defstate aud :start (-> config :jwt :audience))
(defstate ttl :start (-> config :jwt :ttl))
(defstate private-key :start (-> config :jwt :private-key keys/private-key))
(defstate public-key :start (-> config :jwt :public-key keys/public-key))

(defn create-jwt
  [char-id {:keys [exp]}]
  (let [now (ZonedDateTime/now (ZoneId/systemDefault))
        claim {:iss iss
               :sub char-id
               :aud aud
               :iat (.toEpochSecond now)
               :exp (or exp (.toEpochSecond (.plus now ttl ChronoUnit/HOURS)))}]
    (jwt/sign claim private-key {:alg :es256 :header {:typ "JWT"}})))

(defn validate-jwt [jwt-str]
  (let [claims (jwt/unsign jwt-str public-key {:alg :es256})]
    (when (or (not= aud (:aud claims))
              (not= iss (:iss claims)))
      (throw (ex-info "claim incorrect" {})))
    claims))

(comment
  (validate-jwt (create-jwt "123" nil))
  :rcf)
