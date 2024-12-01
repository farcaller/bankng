(ns net.dracones.bankng.pb-mucklet.core
  (:require [net.dracones.bankng.proto-tools.interface :refer [map->proto proto->map]]
            [manifold.deferred :as d]
            [mount.core :refer [defstate]])
  (:import [io.grpc Grpc InsecureChannelCredentials]
           [net.dracones.bankng.mucklet LookupCharacterRequest MuckletGrpc SendMessageRequest]))

(def mucklet-server-endpoint "localhost:8081")

(defn build-channel
  []
  (-> (Grpc/newChannelBuilder mucklet-server-endpoint (InsecureChannelCredentials/create))
      (.build)))

(defstate channel
  :start (build-channel)
  :stop (.shutdown channel))

(defn build-stub
  [channel]
  (MuckletGrpc/newFutureStub channel))

(defstate stub
  :start (build-stub channel))

(defn lookup-character
  [full-name]
  (d/chain
   (d/->deferred (.lookupCharacter stub (map->proto LookupCharacterRequest {:full-name full-name})))
   proto->map))

(defn send-message
  [char-id msg]
  (d/chain
   (d/->deferred (.sendMessage stub (map->proto SendMessageRequest {:char-id char-id :message msg})))
   proto->map))

(comment
  (build-channel)
  (-> (LookupCharacterRequest/newBuilder)
      (.setFullName "test")
      (.build))

  @(lookup-character "birb crowley")
  @(send-message "cajd55e9gbrqf703lcvg" "mlem")

  *1

  (map->proto LookupCharacterRequest {:full-name "test"})

  :rcf)
