(ns bankng.grpc-auth.ifc
  (:require [bankng.grpc-auth.core :as core]))

(defn auth-interceptor []
  (core/auth-interceptor))

(defn context-subject []
  (.get core/jwt-sub-claim-key))
