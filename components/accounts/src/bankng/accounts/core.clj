(ns bankng.accounts.core
  (:require [xtdb.api :as xt]
            [xtdb.db :as xt.db]
            [xtdb.codec :refer [crux->xt]]
            [mount.core :refer [defstate]]
            [bankng.config.ifc :refer [config]]
            [bankng.xtdb.ifc :as db]
            [bankng.iban.ifc :as iban]))

(defn account-id [account-number]
  (keyword "account" (iban/calculate-iban account-number)))

(comment
  (require '[mount.core :as mount])
  (mount/stop)
  (mount/start)
  #> db/conn
  #> (xt/status db/conn)

  #_(xt/submit-tx db/conn
                  [[:put-docs :accounts {:xt/id "WY00123" :name "account 123" :balance 100}]
                   [:put-docs :accounts {:xt/id "WY00789" :name "account 789" :balance 100}]])

  (xt/submit-tx db/conn [[::xt/put {:xt/id {:account "WY00123"} :acc/name "account 123" :acc/balance 98}]
                         [::xt/put {:xt/id {:account "WY00789"} :acc/name "account 789" :acc/balance 98}]])


  ; v1: get account info
  (xt/q (xt/db db/conn) '{:find [(pull ?e [*])]
                          :where [[?e :acc/name "account 123"]]})
  
  #> (xt/q (xt/db db/conn) '{:find [(pull ?e [* {:from [*]} {:to [*]}])]
                          :where [[?e :from _]]})

  ; v1: get account document
  (xt/entity (xt/db db/conn) {:account "WY00123"})

  ; v1: get history of changes
  #> (xt/entity-history (xt/db db/conn) {:account "WY00123"} :asc {:with-docs? true})

  ; v1: get all transactions
  (with-open [tx-log (xt/open-tx-log db/conn -1 true)]
    (let [iterator (iterator-seq tx-log)]
      (map identity iterator)))

  (xt/submit-tx db/conn
                [[:delete {:from :accounts
                           :bind [{:xt/id "deadbeef"}
                                  {:name "account 3"}]}]])

  (xt/q db/conn '(-> (from :xt/txs [* {:xt/id 15}])
                     (order-by xt/id)))

  (xt/q db/conn '(from :accounts [name balance #_{:xt/id "WY00123"}]))
  (xt/q db/conn '(from :transactions [*]))

  (xt/submit-tx
   db/conn
   [[::xt/put {:xt/id :create-transfer
               :xt/fn '(fn [ctx [from to amount txid]]
                         (let [db (xtdb.api/db ctx)
                               from-doc (xtdb.api/entity db from)
                               from-balance (:acc/balance from-doc)
                               to-doc (xtdb.api/entity db to)
                               to-balance (:acc/balance to-doc)]
                           (when (< (- from-balance amount) 0)
                             (throw (ex-info "insufficient balance" {:acc/balance from-balance
                                                                     :acc/amount amount})))
                           [[::xt/put {:xt/id txid :from from :to to :amount amount}]
                            [::xt/put (update from-doc :acc/balance - amount)]
                            [::xt/put (update to-doc :acc/balance + amount)]]))}]])

  (update {:balance 10} :balance - 1)

  (def txid
    (xt/submit-tx
     db/conn [[::xt/fn :create-transfer [{:account "WY00123"} {:account "WY00789"} 500 (random-uuid)]]]))

  (xt/tx-committed? db/conn txid)
  (xt/await-tx db/conn txid)

  ; v1: why failed?
  (require '[clojure.set :as set])
  (let [tx-id (:xtdb.api/tx-id txid)
        document-store #> (:document-store db/conn)
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
                                    #> (:txdb.tx.event/tx-events #> (first (iterator-seq tx-log)))))]
        #> doc-hashes
        #_(filter :crux.db.failed?
                  (vals (strict-fetch-docs document-store doc-hashes))))))

  #> (with-open [tx-log (xt.db/open-tx-log (:tx-log db/conn) -1 {})]
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
