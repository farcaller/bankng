(ns bankng.jwt.ifc
  (:require [bankng.jwt.core :as core]))

(defn create-jwt
  ([char-id opts]
   (core/create-jwt char-id opts))
  ([char-id]
   (core/create-jwt char-id nil)))

(defn validate-jwt [jwt]
  (core/validate-jwt jwt))
