(ns bankng.mount.core
  (:require [mount.core :as mount]
            [shutdown.core :as shutdown]
            [bankng.grpc-server.core :as grpc-sever])
  (:gen-class))

(defn -main [& args]
  (mount/start)
  (shutdown/add-hook! ::mount-stop mount/stop)
  (.awaitTermination grpc-sever/server))
