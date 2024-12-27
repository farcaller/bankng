(ns bankng.mucklet-proxy.core
  (:require [bankng.otel.ifc]
            ; otel always comes first because it sets up interceptors
            
            [botmaster.bot :as bot]
            [oops.core :refer [oget]]
            ["@grpc/grpc-js" :as grpc]
            [bankng.pb-mucklet.ifc :refer [mucklet-service]]
            [bankng.mucklet-service.ifc :refer [lookup-character send-message]]))

(def server
  (doto (grpc/Server.)
    (.addService mucklet-service #js {:lookupCharacter #(lookup-character %1 %2)
                                      :sendMessage #(send-message %1 %2)})))

(defn start
  [address]
  (.bindAsync server address (grpc/ServerCredentials.createInsecure) #(println "server running at" address)))

(defn stop
  []
  (.tryShutdown server #()))

(defn init
  []
  (bot/connect-client! :scambanker true)
  (start (oget js/process "env.SERVER_LISTEN_ADDRESS"))
  (.on js/process "SIGINT" #(.exit js/process))
  (.on js/process "SIGTERM" #(.exit js/process)))
