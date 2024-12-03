(ns net.dracones.bankng.grpc-auth.interface
  (:require [net.dracones.bankng.grpc-auth.core :as core]))

(defn auth-interceptor []
  (core/auth-interceptor))
