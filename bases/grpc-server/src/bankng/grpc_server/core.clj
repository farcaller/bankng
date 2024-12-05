(ns bankng.grpc-server.core
  (:require [bankng.frontend-service.ifc :as fs]
            [bankng.accounts-service.ifc :as as]
            [bankng.grpc-auth.ifc :as grpc-auth]
            [mount.core :refer [defstate]]
            [bankng.config.ifc :refer [config]])
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
