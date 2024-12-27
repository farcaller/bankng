(ns bankng.accounts-service.core
  (:require [bankng.accounts.ifc :as accounts]
            [bankng.grpc-auth.ifc :as auth]
            [bankng.pb-frontend.ifc :as fpb]
            [bankng.pb-mucklet.ifc :as mu]
            [bankng.proto-tools.ifc :refer [->err ->status code? date->timestamp
                                            ex->status map->proto proto->map]]
            [manifold.deferred :as d]
            [taoensso.telemere :as log])
  (:import [bankng
            Account
            Correspondent
            ListAccountsReply
            ListAccountsReply$Builder
            ListAccountsRequest
            ListTransactionsReply
            ListTransactionsRequest
            Transaction]
           [io.grpc Status]
           [io.grpc.stub StreamObserver]))

(defn return-error [err]
  (log/error! err)
  (let [st (->status err)
        code (code? st)]
    (if (= :UNKNOWN code)
      (.asRuntimeException (ex->status Status/INTERNAL err "Internal error. Please try again later."))
      (.asRuntimeException st))))

(defn get-char-id-or-die! []
  (if-let [sub (auth/context-subject)]
    sub
    (throw (->err Status/UNAUTHENTICATED "Not authenticated."))))

(defn accounts->proto [accounts]
  (let [^ListAccountsReply$Builder reply-pb (ListAccountsReply/newBuilder)]
    (doseq [acc accounts]
      (.addAccounts
       reply-pb
       (map->proto
        Account
        {:iban (name (:xt/id acc))
         :name (:account/name acc)
         :currency (-> acc :account/currency :currency/name)
         :currency_code (-> acc :account/currency :currency/symbol)
         :balance (-> acc :account/balance str)})))
    (.build reply-pb)))

(defn list-accounts [^ListAccountsRequest _request ^StreamObserver responseObserver]
  (-> (d/let-flow [char-id (get-char-id-or-die!)
                   accounts (accounts/list-accounts char-id)
                   reply-pb (accounts->proto accounts)]
                  (.onNext responseObserver reply-pb)
                  (.onCompleted responseObserver))
      (d/catch #(.onError responseObserver (return-error %)))))

(def ^:dynamic *correspondent-cache* nil)

(defn lookup-correspondent! [char-id]
  (if-let [correspondent (get *correspondent-cache* char-id)]
    correspondent
    (let [correspondent (mu/lookup-character :char-id char-id)]
      (set! *correspondent-cache* (assoc *correspondent-cache* char-id correspondent))
      correspondent)))

(defn transactions->proto [transactions]
  (binding [*correspondent-cache* {}]
    (let [reply-pb (ListTransactionsReply/newBuilder)]
      (doseq [txn transactions]
        (let [correspondent (lookup-correspondent! (name (:transaction/correspondent txn)))]
          (.addTransactions
           reply-pb
           (map->proto
            Transaction
            {:correspondent (map->proto Correspondent {:name (str (:first-name correspondent) " " (:last-name correspondent))
                                                       :pfp_url (:pfp-url correspondent)})
             :amount (str (when (= :credit (:transaction/direction txn)) "-") (:transaction/amount txn))
             :timestamp (date->timestamp (:transaction/time txn))}))))
      (.build reply-pb))))

(defn list-transactions [^ListTransactionsRequest request ^StreamObserver responseObserver]
  (-> (d/let-flow [char-id (get-char-id-or-die!)
                   req (proto->map request)
                   iban (:iban req)
                   txns (accounts/list-transactions char-id iban)
                   reply-pb (transactions->proto txns)]
                  (.onNext responseObserver reply-pb)
                  (.onCompleted responseObserver))
      (d/catch #(.onError responseObserver (return-error %)))))

(defn create-service []
  (fpb/accounts-impl {:list-accounts #'list-accounts
                      :list-transactions #'list-transactions}))
