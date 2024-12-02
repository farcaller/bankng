(ns net.dracones.bankng.grpc-server.core
  (:require [net.dracones.bankng.frontend-service.interface :as fe]
            [mount.core :refer [defstate]]
            [net.dracones.bankng.config.interface :refer [config]])
  (:import [io.grpc Grpc InsecureServerCredentials]))

(defn create-server [port]
  (-> (Grpc/newServerBuilderForPort port (InsecureServerCredentials/create))
      (.addService (fe/create-service))
      (.build)))

(defstate server
  :start (doto
           (create-server (-> config :grpc :port))
           (.start))
  :stop (.shutdown server))

(comment
  server
  :rcf)
