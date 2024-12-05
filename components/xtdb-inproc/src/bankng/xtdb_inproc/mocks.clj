(ns bankng.xtdb-inproc.mocks
  (:require [bankng.xtdb.interface :as db]
            [xtdb.api :as xt]
            [bankng.iban.interface :refer [calculate-iban]]))

(defn account-id [iban]
  (keyword "account" (calculate-iban iban)))

(defn seed []
  (xt/submit-tx
   db/conn
   [[::xt/put {:xt/id :currency/SCAM
               :currency/name "SCAM"
               :currency/symbol "₷"}]

    [::xt/put {:xt/id :customer/cajd55e9gbrqf703lcvg
               :customer/full-name "Shinyuu Wolfy"
               :customer/accounts [(account-id 123)]}]

    [::xt/put {:xt/id (account-id 123)
               :account/name "Personal"
               :account/currency :currency/SCAM
               :account/balance 101}]
    [::xt/put {:xt/id (account-id 789)
               :acc/name "Business"
               :acc/currency :currency/SCAM
               :acc/balance 101}]]))

(comment
  (seed)
  ; get a customer
  (xt/pull (xt/db db/conn) '[*] :customer/cajd55e9gbrqf703lcvg)

  ; get the account details
  (xt/pull (xt/db db/conn) '[* {:account/currency [*]} {:customer/_accounts [:customer/full-name]}] (account-id 123))
  
  ; get the number of accounts per customer
  (xt/q
   (xt/db db/conn)
   '{:find [(count ?ac)]
     :where [[?c :customer/full-name "Shinyuu Wolfy"]
             [?c :customer/accounts ?ac]]})
  :rcf)
