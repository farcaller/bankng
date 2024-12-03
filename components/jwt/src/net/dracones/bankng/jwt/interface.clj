(ns net.dracones.bankng.jwt.interface
  (:require [net.dracones.bankng.jwt.core :as core]))

(defn create-jwt
  ([char-id opts]
   (core/create-jwt char-id opts))
  ([char-id]
   (core/create-jwt char-id nil)))

(defn validate-jwt [jwt]
  (core/validate-jwt jwt))
