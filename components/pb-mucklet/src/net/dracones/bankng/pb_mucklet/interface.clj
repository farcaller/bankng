(ns net.dracones.bankng.pb-mucklet.interface
  (:require [net.dracones.bankng.pb-mucklet.core :as core]))

(defn lookup-character
  [full-name]
  (core/lookup-character full-name))

(defn send-message
  [char-id msg]
  (core/send-message char-id msg))
