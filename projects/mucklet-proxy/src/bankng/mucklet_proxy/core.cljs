(ns bankng.mucklet-proxy.core
  (:require [botmaster.bot :as bot]
            [bankng.mucklet-proxy.grpc :as grpc]
            [oops.core :refer [oget]]))

(defn main!
  []
  (bot/connect-client! :scambanker true)
  (grpc/start (oget js/process "env.SERVER_LISTEN_ADDRESS"))
  (.on js/process "SIGINT" #(.exit js/process))
  (.on js/process "SIGTERM" #(.exit js/process)))
