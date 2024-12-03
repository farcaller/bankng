(ns net.dracones.bankng.accounts.interface
  (:require [net.dracones.bankng.accounts.core :as core]
            [net.dracones.bankng.accounts.iban :as iban]))

(defn calculate-iban [account-number]
  (iban/calculate-local-iban account-number))
