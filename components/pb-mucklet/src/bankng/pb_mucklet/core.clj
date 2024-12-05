(ns bankng.pb-mucklet.core
  (:require [bankng.proto-tools.ifc :refer [map->proto proto->map]]
            [manifold.deferred :as d]
            [mount.core :refer [defstate]]
            [bankng.config.ifc :refer [config]])
  (:import [io.grpc Grpc InsecureChannelCredentials ManagedChannel]
           [bankng.mucklet LookupCharacterRequest MuckletGrpc SendMessageRequest MuckletGrpc$MuckletFutureStub]))

(defstate mucklet-server-endpoint :start (-> config :mucklet-server :url))

(defn build-channel ^ManagedChannel []
  (-> (Grpc/newChannelBuilder mucklet-server-endpoint (InsecureChannelCredentials/create))
      (.build)))

(defstate channel
  :start (build-channel)
  :stop (.shutdown ^ManagedChannel channel))

(defn build-stub
  ^MuckletGrpc$MuckletFutureStub [channel]
  (MuckletGrpc/newFutureStub channel))

(defstate ^MuckletGrpc$MuckletFutureStub stub
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
