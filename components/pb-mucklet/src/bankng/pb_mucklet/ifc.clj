(ns bankng.pb-mucklet.ifc
  (:require [bankng.pb-mucklet.core :as core]))

(defn lookup-character
  [& {:keys [full-name char-id] :as opts}]
  (core/lookup-character opts))

(defn send-message
  [char-id msg]
  (core/send-message char-id msg))
