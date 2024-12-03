(ns net.dracones.bankng.codesender.interface
  (:require [net.dracones.bankng.codesender.core :as core]))

(defn send-code
  "Generates a new OTP for the given session, stores it, and sends it."
  [session-id char-id]
  (core/send-code session-id char-id))

(defn verify-code
  "Verifies the code exists for the given session and it's correct.
   
   Will either return the char-id for the session or throw."
  [session-id code]
  (core/verify-code session-id code))
