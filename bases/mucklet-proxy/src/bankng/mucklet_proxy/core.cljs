(ns bankng.mucklet-proxy.core
  (:require [bankng.otel.ifc]
            ; otel always comes first because it sets up interceptors

            [botmaster.bot :as bot]
            [oops.core :refer [oget]]
            ["@grpc/grpc-js" :as grpc]
            [bankng.pb-mucklet.ifc :refer [mucklet-service]]
            [bankng.mucklet-service.ifc :refer [lookup-character send-message]]
            [mount.core :as mount])
  (:require-macros [mount.core :refer [defstate]]))

(defstate listen-address :start (oget js/process "env.SERVER_LISTEN_ADDRESS"))

(defstate server
  :start (doto (grpc/Server.)
           (.addService mucklet-service #js {:lookupCharacter #(lookup-character %1 %2)
                                             :sendMessage #(send-message %1 %2)})
           (.bindAsync @listen-address
                       (grpc/ServerCredentials.createInsecure)
                       #(println "server running at" @listen-address)))
  :stop (.tryShutdown server #()))

(defn init
  []
  (bot/connect-client! :scambanker true)
  (mount/start)
  (.on js/process "SIGINT" #(.exit js/process))
  (.on js/process "SIGTERM" #(.exit js/process)))
