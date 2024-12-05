(ns bankng.iban.core
  (:require [mount.core :refer [defstate]]
            [bankng.config.ifc :refer [config]]))

(defstate country-code :start (-> config :accounts :country-code))
(defstate sort-code :start (-> config :accounts :sort-code))

(def char-offset (- 10 (int \A)))

(defn char-to-number
  [char]
  (if (Character/isDigit char)
    char
    (str (+ (int char) char-offset))))

(defn calculate-checksum
  [numeric-representation]
  (mod (BigInteger. numeric-representation) 97))

(defn iban-checksum
  "Calculates the IBAN for the given country-code, sort-code, and account-number."
  [country-code sort-code account-number]
  (let [preliminary-iban (str sort-code account-number country-code "00")
        numeric-representation (apply str (map char-to-number preliminary-iban))
        checksum (- 98 (calculate-checksum numeric-representation))]
    (format "%s%02d%s%08d" country-code (long checksum) sort-code account-number)))

(defn calculate-local-iban
  [account-number]
  (iban-checksum country-code sort-code account-number))
