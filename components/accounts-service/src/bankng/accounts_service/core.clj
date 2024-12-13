(ns bankng.accounts-service.core
  (:require [bankng.pb-frontend.ifc :as fpb]
            [bankng.accounts.ifc :as accounts]
            [bankng.grpc-auth.ifc :as auth]
            [bankng.proto-tools.ifc :refer [map->proto proto->map ->status code? ex->status ->err]]
            [taoensso.telemere :as log]
            [manifold.deferred :as d]
            [clojure.string :as s])
  (:import [io.grpc Status]
           [io.grpc.stub StreamObserver]
           [bankng ListAccountsRequest ListAccountsReply ListAccountsReply$Builder Account]))

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

(defn create-service []
  (fpb/accounts-impl {:list-accounts #'list-accounts}))

(comment
  (accounts->proto (accounts/list-accounts "cajd55e9gbrqf703lcvg"))
  :rcf)
