(ns net.dracones.bankng.accounts-service.interface
  (:require [net.dracones.bankng.accounts-service.core :as core]))

(defn create-service []
  (core/create-service))
