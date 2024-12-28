(ns bankng.pb-mucklet.client
  (:require [bankng.proto-tools.ifc :refer [map->proto proto->map]]
            [mount.core :refer [defstate]]
            [bankng.config.ifc :refer [config]]
            [clojure.java.io :as io]
            [clojure.edn :as edn])
  (:import [io.grpc Grpc InsecureChannelCredentials ManagedChannel]
           [bankng.mucklet LookupCharacterRequest MuckletGrpc SendMessageRequest MuckletGrpc$MuckletBlockingStub]))

(defstate mucklet-server-endpoint :start (-> config :mucklet-server :url))

(defn convert-to-java [data]
  (cond
    (keyword? data) (name data)
    (number? data) (double data)
    (map? data) (into {} (map (fn [[k v]] [(convert-to-java k) (convert-to-java v)]) data))
    (or (vector? data) (list? data)) (mapv convert-to-java data)
    :else data))

(defstate service-config
  :start (->
          "bankng/mucklet_service/service_config.edn"
          io/resource
          slurp
          edn/read-string
          convert-to-java))

(defn build-channel ^ManagedChannel []
  (-> (Grpc/newChannelBuilder mucklet-server-endpoint (InsecureChannelCredentials/create))
      (.defaultServiceConfig service-config)
      (.enableRetry)
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
  (mount.core/stop #'bankng.pb-mucklet.client/service-config #'bankng.pb-mucklet.client/channel #'bankng.pb-mucklet.client/stub)
  (mount.core/start #'bankng.pb-mucklet.client/service-config #'bankng.pb-mucklet.client/channel #'bankng.pb-mucklet.client/stub)
  (mount.core/start #'bankng.pb-mucklet.client/service-config)
  service-config
  
  (build-channel)
  (-> (LookupCharacterRequest/newBuilder)
      (.setFullName "test")
      (.build))

  (lookup-character :full-name "birb crowley")
  (send-message "cajd55e9gbrqf703lcvg" "mlem")

  *1

  (map->proto LookupCharacterRequest {:full-name "test"})

  :rcf)
