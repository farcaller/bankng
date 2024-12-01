(ns net.dracones.bankng.grpc-server.core
  (:require [net.dracones.bankng.frontend-service.interface :as fe]
            [mount.core :refer [defstate]])
  (:import [io.grpc Grpc InsecureServerCredentials]))

(defn create-server [port]
  (-> (Grpc/newServerBuilderForPort port (InsecureServerCredentials/create))
      (.addService (fe/create-service))
      (.build)))

(defstate server
  :start (doto
           (create-server 50051)
           (.start))
  :stop (.shutdown server))

(comment
  server
  :rcf)
