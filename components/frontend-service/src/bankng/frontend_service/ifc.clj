(ns bankng.frontend-service.ifc
  (:require [bankng.frontend-service.core :as core])
  (:import [bankng AuthGrpc$AuthImplBase]))

(defn create-service ^AuthGrpc$AuthImplBase []
  (core/create-service))
