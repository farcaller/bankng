(ns net.dracones.bankng.codesender.interface
  (:require [net.dracones.bankng.codesender.core :as core]))

(defn send-code [char-id]
  (core/send-code char-id))
