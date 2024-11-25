(ns net.dracones.bankng.grpc-server.core
  (:require [net.dracones.bankng.frontend-service.interface :as fe])
  (:import [io.grpc Grpc InsecureServerCredentials]))

(defn create-server [port]
  (-> (Grpc/newServerBuilderForPort port (InsecureServerCredentials/create))
      (.addService (fe/create-service))
      (.build)))

(defonce server (atom (create-server 50051)))

(defn start [] (.start @server))

(defn stop [] (.shutdown @server))

(comment
  server
  (reset! server (create-server 50051))
  (start)
  (stop)
  :rcf)
