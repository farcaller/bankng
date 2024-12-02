(ns net.dracones.bankng.datomic.core
  (:require [datomic.client.api :as d]))

(defn create-temp-db []
  (d/client {:server-type :datomic-local
             :storage-dir :mem
             :system "dev"}))

(comment
  (def db (create-temp-db))

  (d/create-database db {:db-name "accounts"})
  (def conn (d/connect db {:db-name "accounts"}))

  (d/transact
   conn {:tx-data [{:db/ident :account/name
                    :db/valueType :db.type/string
                    :db/cardinality :db.cardinality/one
                    :db/doc "Account name"}
                   {:db/ident :account/balance
                    :db/valueType :db.type/long
                    :db/cardinality :db.cardinality/one
                    :db/doc "Account balance"}
                   {:db/ident :account/iban
                    :db/valueType :db.type/string
                    :db/unique :db.unique/value
                    :db/cardinality :db.cardinality/one
                    :db/doc "Account IBAN"}

                   {:db/ident :transaction/id
                    :db/valueType :db.type/uuid
                    :db/cardinality :db.cardinality/one
                    :db/unique :db.unique/value
                    :db/doc "Transaction ID"}
                   {:db/ident :transaction/from
                    :db/valueType :db.type/ref
                    :db/cardinality :db.cardinality/one
                    :db/doc "From account"}
                   {:db/ident :transaction/to
                    :db/valueType :db.type/ref
                    :db/cardinality :db.cardinality/one
                    :db/doc "To account"}
                   {:db/ident :transaction/amount
                    :db/valueType :db.type/long
                    :db/cardinality :db.cardinality/one
                    :db/doc "Transaction amount"}]})

  (d/transact
   conn {:tx-data [{:account/name "account 123"
                    :account/iban "WY00123"
                    :account/balance 100}
                   {:account/name "account 789"
                    :account/iban "WY00789"
                    :account/balance 100}]})

  (def ctfn (d/function
             {:lang   "clojure"
              :params '[db from-iban to-iban amount txid]
              :code
              '(let [from-doc (datomic.api/entity db [:account/iban from-iban])
                     from-balance (:account/balance from-doc)
                     to-doc (datomic.api/entity db [:account/iban to-iban])
                     to-balance (:account/balance to-doc)]
                 (when-not (and from-doc to-doc)
                   (datomic.api/cancel {:cognitect.anomalies/category :cognitect.anomalies/incorrect
                                        :cognitect.anomalies/message "account not found"
                                        :acc/from from-iban
                                        :acc/to to-iban}))
                 (when (< (- from-balance amount) 0)
                   (datomic.api/cancel {:cognitect.anomalies/category :cognitect.anomalies/incorrect
                                        :cognitect.anomalies/message "insufficient balance"
                                        :acc/balance from-balance
                                        :acc/amount amount}))
                 (println "d" from-doc from-balance to-doc to-balance)
                 [[:db/add (:db/id from-doc) :account/balance (- from-balance amount)]
                  [:db/add (:db/id to-doc) :account/balance (+ from-balance amount)]
                  [:db/add "tx" :transaction/from (:db/id from-doc)]
                  [:db/add "tx" :transaction/to (:db/id to-doc)]
                  [:db/add "tx" :transaction/amount amount]
                  [:db/add "tx" :transaction/id txid]
                  [:db/add "datomic.tx" :db/doc "funds transfer"]])}))

  @(d/transact
    conn [{:db/ident :create-transfer
           :db/fn ctfn}])

  (ctfn (d/db conn) "WY00123" "WY0078l9" 1 2)

  (defn create-transfer [db from-iban to-iban amount txid]
    (let [from-doc (ffirst (d/q '[:find (pull ?e [*])
                                  :in $ ?iban
                                  :where [?e :account/iban ?iban]]
                                db from-iban))
          from-balance (:account/balance from-doc)
          to-doc (ffirst (d/q '[:find (pull ?e [*])
                                :in $ ?iban
                                :where [?e :account/iban ?iban]]
                              db to-iban))
          to-balance (:account/balance to-doc)]
      (when-not (and from-doc to-doc)
        (throw (ex-info "account not found" {:cognitect.anomalies/category :cognitect.anomalies/incorrect
                                             :cognitect.anomalies/message "account not found"
                                             :acc/from from-iban
                                             :acc/to to-iban})))
      (when (< (- from-balance amount) 0)
        (throw (ex-info "insufficient balance" {:cognitect.anomalies/category :cognitect.anomalies/incorrect
                                                :cognitect.anomalies/message "insufficient balance"
                                                :acc/balance from-balance
                                                :acc/amount amount})))
      (println "d" from-doc from-balance to-doc to-balance)
      [[:db/add (:db/id from-doc) :account/balance (- from-balance amount)]
       [:db/add (:db/id to-doc) :account/balance (+ from-balance amount)]
       [:db/add "tx" :transaction/from (:db/id from-doc)]
       [:db/add "tx" :transaction/to (:db/id to-doc)]
       [:db/add "tx" :transaction/amount amount]
       [:db/add "tx" :transaction/id txid]
       [:db/add "datomic.tx" :db/doc "funds transfer"]]))

  (try
    (d/transact
     conn {:tx-data
           [['net.dracones.bankng.datomic.core/create-transfer "WY00123" "WY00789" 1 (random-uuid)]
            ['net.dracones.bankng.datomic.core/create-transfer "WY00123" "WY00789" 1 (random-uuid)]]})
    (catch Exception e
      (println (ex-data e))))

  (d/with
   (d/db conn) [[net.dracones.bankng.datomic.core/create-transfer "WY00123" "WY00789" 10 (random-uuid)]])

  (d/q '[:find (pull ?e [*])
         :in $
         :where [?e :account/iban _]]
       (d/db conn))

  (d/q '[:find (pull ?e [*]) ?q
         :in $
         :where [?e :transaction/id ?q]]
       (d/db conn))

  (d/pull (d/db conn) '[*])

  ; datomic local: find all transactions
  (d/q
   '[:find (pull ?tx [*])
     :where
     [?tx :db/txInstant]]
   (d/db conn))
  
  ; datomic local: pull all facts from given transaction
  (d/q
   '[:find 
       ?tx ?e ?av ?v ?op
     :in $ ?tx
     :where
     [?e ?a ?v ?tx ?op]
     [(= ?op true)]
     [?a :db/ident ?av]
     [?tx :db/txInstant ?tx-time]
     ;[?tx ?tk ?tv]
     ;[?tk :db/ident ?tkv]
     ]
   (d/history (d/db conn))
   13194139533323)

  (d/q '[:find ?e ?a-ident ?v ?tx ?op
         :in $ ?log ?tx
         :where
         #_[(tx-ids ?log ?t1 ?t2) [?tx ...]]
         [(tx-data ?log ?tx) [[?e ?a ?v _ ?op]]]
         [?a :db/ident ?a-ident]]
       (d/db conn) (d/log conn) 1027)

  :rcf)
