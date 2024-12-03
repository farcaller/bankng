(ns net.dracones.bankng.iban.interface
  (:require [net.dracones.bankng.iban.core :as core]))

(defn calculate-iban [account-number]
  (core/calculate-local-iban account-number))
