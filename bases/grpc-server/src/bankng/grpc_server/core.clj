(ns bankng.grpc-server.core
  (:require [bankng.frontend-service.interface :as fs]
            [bankng.accounts-service.interface :as as]
            [bankng.grpc-auth.interface :as grpc-auth]
            [mount.core :refer [defstate]]
            [bankng.config.interface :refer [config]])
  (:import [io.grpc Grpc InsecureServerCredentials]))

(defn create-server [port]
  (-> (Grpc/newServerBuilderForPort port (InsecureServerCredentials/create))
      (.addService (fs/create-service))
      (.addService (as/create-service))
      (.intercept (grpc-auth/auth-interceptor))
      (.build)))

(defstate server
  :start (doto
           (create-server (-> config :grpc :port))
           (.start))
  :stop (.shutdown server))

(comment
  server
  :rcf)
