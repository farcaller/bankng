(ns bankng.mucklet-bot.ifc
  (:require [bankng.mucklet-bot.core :as core]))

(defn lookup-character
  "Looks up a character by full name. Returns a promise that resolves to a character object."
  [& {:keys [full-name char-id] :as opts}]
  (core/lookup-character opts))

(defn send-message
  "Sends a message to a character. Returns a promise that resolves or throws."
  [char-id message]
  (core/send-message char-id message))
