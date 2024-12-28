(ns bankng.accounts.core
  (:require [xtdb.api :as xt]
            [xtdb.db :as xt.db]
            [xtdb.codec :refer [crux->xt]]
            [mount.core :refer [defstate]]
            [bankng.config.ifc :refer [config]]
            [bankng.xtdb.ifc :as db]
            [bankng.iban.ifc :as iban]
            [mount.core :refer [defstate]]
            [taoensso.telemere :as t]))

(defn account-id [account-number]
  (keyword "account" (iban/calculate-iban account-number)))

(defn list-accounts [char-id]
  (:customer/accounts (db/pull [{:customer/accounts [* {:account/currency [*]}]}] (keyword "customer" char-id))))

(defn list-transactions
  [customer-id account-id & {:keys [limit offset-ts] :or {limit 10 offset-ts nil}}]
  (let [where '[[?aid :xt/id ?account-id]
                [?customer-eid :customer/accounts ?aid]
                (or
                 (and [?txid :transaction/from ?aid]
                      [(identity :credit) ?direction]
                      [?txid :transaction/to ?cid])
                 (and [?txid :transaction/to ?aid]
                      [(identity :debit) ?direction]
                      [?txid :transaction/from ?cid]))

                [(get-start-valid-time ?txid) ?tx-time]]]
    (map
     (fn [{:keys [time direction info correspondent]}]
       (assoc info
              :transaction/time time
              :transaction/direction direction
              :transaction/correspondent (-> correspondent :accounts :xt/id)))
     (xt/q (xt/db db/conn)
           {:find '[?tx-time
                    ?direction
                    (pull ?txid [*])
                    (pull ?cid [{(:customer/_accounts {:as :accounts :cardinality :one}) [:xt/id]}])]
            :in '[?account-id ?customer-eid]
            :keys '[time direction info correspondent]
            :order-by '[[?tx-time :desc]]
            :limit limit
            :where (if offset-ts
                     (into [] (concat where [[(list '< '?tx-time offset-ts)]]))
                     where)}
           (keyword "account" account-id) (keyword "customer" customer-id)))))

(defn ensure-function!
  [name func]
  (let [dbfunc (xt/entity (xt/db db/conn) name)]
    (when (or (nil? dbfunc)
              (not= dbfunc func))
      (t/log! {:level :info :data {:name name}} "Updating the function definition")
      (xt/submit-tx db/conn [[::xt/put {:xt/id name :xt/fn func}]]))))

(def functions
  {:fn/create-transfer
   '(fn [ctx [from to amount txid]]
      (assert (= (type amount) java.math.BigDecimal) "The value must be BigDecimal")
      (when-not (parse-uuid txid)
        (throw (ex-info "invalid txid" {:txid txid})))
      (let [db (xtdb.api/db ctx)
            txid (keyword "transaction" txid)
            old-tx (xtdb.api/entity db txid)
            _ (when old-tx (throw (ex-info "txid already exists" {:txid txid})))
            from-doc (xtdb.api/entity db from)
            from-balance (:account/balance from-doc)
            to-doc (xtdb.api/entity db to)
            to-balance (:account/balance to-doc)]
        (when (< (- from-balance amount) 0)
          (throw (ex-info "insufficient balance" {:account/balance from-balance
                                                  :account/amount amount})))
        [[::xt/put {:xt/id txid
                    :transaction/from from
                    :transaction/to to
                    :transaction/amount amount}]
         [::xt/put (update from-doc :account/balance - amount)]
         [::xt/put (update to-doc :account/balance + amount)]]))})

(defstate db-functions :start (doseq [[name func] functions] (ensure-function! name func)))

(comment
  (list-transactions "cajd55e9gbrqf703lcvg" "WY66RASD00000123" :limit 10)
  (list-accounts "cajd55e9gbrqf703lcvg")

  (mount.core/stop #'bankng.accounts.core/db-functions)
  (mount.core/start #'bankng.accounts.core/db-functions)
  db/conn
  (xt/status db/conn)

  ; v1: get account info
  (xt/q (xt/db db/conn) '{:find [(pull ?e [*])]
                          :where [[?e :account/name _]]})

  ; v1: get account document
  (xt/entity (xt/db db/conn) :account/WY96RASD00000456)

  ; v1: get history of changes
  (xt/entity-history (xt/db db/conn) :account/WY66RASD00000123 :asc {:with-docs? true})

  ; v1: get all transactions
  (with-open [tx-log (xt/open-tx-log db/conn -1 true)]
    (let [iterator (iterator-seq tx-log)]
      (map identity iterator)))
  
  (type (bigdec 0.1))

  ; v1: create transaction
  (def txid
    (xt/submit-tx
     db/conn [[::xt/fn :fn/create-transfer [:account/WY66RASD00000123
                                         :account/WY29RASD00000789
                                         (bigdec 0.1)
                                         (str (random-uuid))]]]))
  (def txid
    (xt/submit-tx
     db/conn [[::xt/fn :fn/create-transfer [:account/WY96RASD00000456
                                         :account/WY66RASD00000123
                                         2
                                         (str (random-uuid))]]]))

  (xt/tx-committed? db/conn txid)
  (xt/await-tx db/conn txid)

  ; v1: transactions for account
  (xt/q (xt/db db/conn) '{:find [(pull ?e [* {:transaction/from [*]}])]
                          :where [[?e :transaction/from _]]})

  (xt/q (xt/db db/conn) {:find '[?tx-time
                                 ?direction
                                 (pull ?txid [*])
                                 (pull ?cid [{:customer/_accounts [:xt/id]}])]
                         :in '[?account-id]
                         :keys '[time direction info correspondent]
                         :order-by '[[?tx-time :desc]]
                         :limit 20
                         :where '[[?aid :xt/id ?account-id]
                                  (or
                                   (and [?txid :transaction/from ?aid]
                                        [(identity :credit) ?direction]
                                        [?txid :transaction/to ?cid])
                                   (and [?txid :transaction/to ?aid]
                                        [(identity :debit) ?direction]
                                        [?txid :transaction/from ?cid]))
                                  [(get-start-valid-time ?txid) ?tx-time]
                                  #_[(< ?tx-time #inst "2024-12-26T18:50:06.996-00:00")]]}
        :account/WY66RASD00000123)

  ; v1: why failed?
  (require '[clojure.set :as set])
  (let [tx-id (:xtdb.api/tx-id txid)
        document-store (:document-store db/conn)
        strict-fetch-docs (fn [document-store doc-hashes]
                            (let [doc-hashes (set doc-hashes)
                                  docs nil #_(xt/fetch-docs document-store doc-hashes)
                                  fetched-doc-hashes (set (keys docs))]
                              (when-not (= fetched-doc-hashes doc-hashes)
                                (throw (IllegalStateException. (str "missing docs: " (pr-str (set/difference doc-hashes fetched-doc-hashes))))))
                              docs))]
    (with-open [tx-log (xt/open-tx-log db/conn (dec tx-id) {})]
      (let [doc-hashes (map #(get % 2)
                            (filter #(= (first %) :crux.tx/fn)
                                    (:txdb.tx.event/tx-events (first (iterator-seq tx-log)))))]
        doc-hashes
        #_(filter :crux.db.failed?
                  (vals (strict-fetch-docs document-store doc-hashes))))))

  (with-open [tx-log (xt.db/open-tx-log (:tx-log db/conn) -1 {})]
    (->> tx-log
         iterator-seq
         (map (fn [e]
                (let [events (:xtdb.tx.event/tx-events e)]
                  (assoc e :events (map (fn [ev]
                                          (xt.db/fetch-docs (:document-store db/conn) (next ev))) events)))))))

  (let [document-store (:document-store db/conn)]
    (with-open [tx-log (xt.db/open-tx-log (:tx-log db/conn) (dec (:xtdb.api/tx-id txid)) {})]
      (let [m
            (->> tx-log
                 iterator-seq
                 first
                 ; narrow to this tx-id
                 :xtdb.tx.event/tx-events ; pull out events
                 (map #(get % 2)) ; get the document-hash of each event
                 set
                 (xt.db/fetch-docs document-store)
                 vals
                 (filter :crux.db.fn/exception)
                 first
                 crux->xt)]
        (-> m (set/rename-keys
               {:crux.db.fn/failed? :juxt.site.xt/failed?
                :crux.db.fn/exception :juxt.site.xt/exception
                :crux.db.fn/message :juxt.site.xt/message
                :crux.db.fn/ex-data :juxt.site.xt/ex-data})))))

  (xt/submit-tx
   db/conn
   [[:put-fn :create-transfer
     '(fn [from to amount txid]
        (let [from-balance (-> (q '(from :accounts [balance {:xt/id $id}])
                                  {:args {:id from}})
                               first
                               :balance)
              to-balance (-> (q '(from :accounts [balance {:xt/id $id}])
                                {:args {:id to}})
                             first
                             :balance)]
          (when (< (- from-balance amount) 0)
            (throw (ex-info "insufficient balance" {:balance from-balance
                                                    :amount amount})))
          [[:put-docs :transactions {:xt/id txid :from from :to to :amount amount}]
           [:update '{:table :accounts
                      :bind [balance {:xt/id $id}]
                      :set {:balance $newbalance}}
            {:id from
             :newbalance (- from-balance amount)}]
           [:update '{:table :accounts
                      :bind [balance {:xt/id $id}]
                      :set {:balance $newbalance}}
            {:id to
             :newbalance (+ to-balance amount)}]]))]])

  (xt/submit-tx
   db/conn
   [[:call :create-transfer "WY00123" "WY00789" 50 (random-uuid)]
    [:call :create-transfer "WY00123" "WY00789" 50 (random-uuid)]])

  (def listener (xt/listen db/conn {::xt/event-type ::xt/indexed-tx
                                    :with-tx-ops? true}
                           (fn [& args] (tap> [:listen args]))))

  (random-uuid)
  (xt/q db/conn
        #_"SELECT _id AS tx_id, system_time AS tx_time FROM xt.txs;"
        #_'(from :xt/txs [*])
        '(-> (from :accounts {:for-valid-time :all-time
                              :bind [* xt/valid-from xt/valid-to xt/tx-time xt/tx-id]})

             #_(from :accounts [xt/id name]
                     :for-valid-time (:from #inst "2020-01-01")
                     :for-system-time (:from #inst "2020-01-01")))
        {:explain? false})
  :rcf)
