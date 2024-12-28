(ns bankng.xtdb-inproc.mocks
  (:require [bankng.xtdb.ifc :as db]
            [xtdb.api :as xt]
            [bankng.iban.ifc :refer [calculate-iban]]))

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
               :customer/accounts [(account-id 123) (account-id 456)]}]

    [::xt/put {:xt/id :customer/cam4cp69gbrqf706fs7g
               :customer/full-name "Birb Crowley"
               :customer/accounts [(account-id 789)]}]

    [::xt/put {:xt/id (account-id 123)
               :account/name "Personal"
               :account/currency :currency/SCAM
               :account/balance (bigdec 101)}]
    [::xt/put {:xt/id (account-id 456)
               :account/name "Savings"
               :account/currency :currency/SCAM
               :account/balance (bigdec 99)}]
    [::xt/put {:xt/id (account-id 789)
               :account/name "Birb's Account"
               :account/currency :currency/SCAM
               :account/balance (bigdec 101)}]

    [::xt/fn :fn/create-transfer [:account/WY66RASD00000123
                                  :account/WY29RASD00000789
                                  (bigdec 1.1)
                                  (str (random-uuid))]]
    
    [::xt/fn :fn/create-transfer [:account/WY96RASD00000456
                                  :account/WY66RASD00000123
                                  (bigdec 2.2)
                                  (str (random-uuid))]]
    
    [::xt/fn :fn/create-transfer [:account/WY66RASD00000123
                                  :account/WY29RASD00000789
                                  (bigdec 3.3)
                                  (str (random-uuid))]]]))

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
