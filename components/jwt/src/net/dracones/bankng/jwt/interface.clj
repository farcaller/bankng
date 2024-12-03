(ns net.dracones.bankng.jwt.interface
  (:require [net.dracones.bankng.jwt.core :as core]))

(defn create-jwt [char-id]
  (core/create-jwt char-id))

(defn validate-jwt [jwt]
  (core/validate-jwt jwt))
