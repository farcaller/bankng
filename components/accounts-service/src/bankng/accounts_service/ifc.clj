(ns bankng.accounts-service.ifc
  (:require [bankng.accounts-service.core :as core])
  (:import [bankng AccountsGrpc$AccountsImplBase]))

(defn create-service ^AccountsGrpc$AccountsImplBase []
  (core/create-service))
