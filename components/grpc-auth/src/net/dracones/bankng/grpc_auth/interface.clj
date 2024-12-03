(ns net.dracones.bankng.grpc-auth.interface
  (:require [net.dracones.bankng.grpc-auth.core :as core]))

(defn auth-interceptor []
  (core/auth-interceptor))

(defonce jwt-sub-claim-key core/jwt-sub-claim-key)
