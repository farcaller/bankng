(ns bankng.mucklet-service.ifc
  (:require [bankng.mucklet-service.core :as core]))

(defn lookup-character
  [^js call ^js callback]
  (core/lookup-character call callback))

(defn send-message
  [^js call ^js callback]
  (core/send-message call callback))
