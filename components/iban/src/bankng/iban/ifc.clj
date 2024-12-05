(ns bankng.iban.ifc
  (:require [bankng.iban.core :as core]))

(defn calculate-iban [account-number]
  (core/calculate-local-iban account-number))
