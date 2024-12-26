(ns bankng.pb-mucklet.core
  (:require [bankng.proto-tools.ifc :refer [map->proto proto->map]]
            [manifold.deferred :as d]
            [mount.core :refer [defstate]]
            [bankng.config.ifc :refer [config]])
  (:import [io.grpc Grpc InsecureChannelCredentials ManagedChannel]
           [bankng.mucklet LookupCharacterRequest MuckletGrpc SendMessageRequest MuckletGrpc$MuckletBlockingStub]))

(defstate mucklet-server-endpoint :start (-> config :mucklet-server :url))

(defn build-channel ^ManagedChannel []
  (-> (Grpc/newChannelBuilder mucklet-server-endpoint (InsecureChannelCredentials/create))
      (.build)))

(defstate channel
  :start (build-channel)
  :stop (.shutdown ^ManagedChannel channel))

(defn build-stub
  ^MuckletGrpc$MuckletBlockingStub [channel]
  (MuckletGrpc/newBlockingStub channel))

(defstate ^MuckletGrpc$MuckletBlockingStub stub
  :start (build-stub channel))

(defn lookup-character
  [& {:keys [full-name char-id]}]
  (when-not (or full-name char-id)
    (throw (ex-info "Either full-name or char-id must be provided" {:full-name full-name :char-id char-id})))
  (when (and full-name char-id)
    (throw (ex-info "Only one of full-name or char-id must be provided" {:full-name full-name :char-id char-id})))
  (let [req (cond-> (LookupCharacterRequest/newBuilder)
              full-name (.setFullName full-name)
              char-id (.setCharId char-id)
              true (.build))
        resp (.lookupCharacter stub req)]
    (proto->map resp)))

(defn send-message
  [char-id msg]
  (->
   (.sendMessage stub (map->proto SendMessageRequest {:char-id char-id :message msg}))
   proto->map))

(comment
  (build-channel)
  (-> (LookupCharacterRequest/newBuilder)
      (.setFullName "test")
      (.build))

  (lookup-character "birb crowley")
  (send-message "cajd55e9gbrqf703lcvg" "mlem")

  *1

  (map->proto LookupCharacterRequest {:full-name "test"})

  :rcf)
