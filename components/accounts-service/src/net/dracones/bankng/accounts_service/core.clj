(ns net.dracones.bankng.accounts-service.core
  (:require [net.dracones.bankng.pb-frontend.interface :as fpb]
            [net.dracones.bankng.proto-tools.interface :refer [map->proto proto->map ->status code? ex->status INTERNAL]]
            [net.dracones.bankng.jwt.interface :as jwt]
            [taoensso.telemere :as log]
            [manifold.deferred :as d])
  (:import [net.dracones.bankng ListAccountsRequest ListAccountsReply]))

(defn return-error [err]
  (log/error! err)
  (let [st (->status err)
        code (code? st)]
    (if (= :UNKNOWN code)
      (.asRuntimeException (ex->status INTERNAL err "Internal error. Please try again later."))
      (.asRuntimeException st))))

(defn list-accounts [request responseObserver]
  (-> (d/let-flow [reply-pb (map->proto ListAccountsReply
                                        {})]
                  (.onNext responseObserver reply-pb)
                  (.onCompleted responseObserver))
      (d/catch #(.onError responseObserver (return-error %)))))

(defn create-service []
  (fpb/accounts-impl {:list-accounts #'list-accounts}))
