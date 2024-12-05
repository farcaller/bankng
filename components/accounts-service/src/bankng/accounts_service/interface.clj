(ns bankng.accounts-service.interface
  (:require [bankng.accounts-service.core :as core]))

(defn create-service []
  (core/create-service))
