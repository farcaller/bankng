(ns bankng.accounts-service.core
  (:require [bankng.pb-frontend.interface :as fpb]
            [bankng.proto-tools.interface :refer [map->proto proto->map ->status code? ex->status]]
            [taoensso.telemere :as log]
            [manifold.deferred :as d])
  (:import [io.grpc Status]
           [io.grpc.stub StreamObserver]
           [bankng ListAccountsRequest ListAccountsReply]))

(defn return-error [err]
  (log/error! err)
  (let [st (->status err)
        code (code? st)]
    (if (= :UNKNOWN code)
      (.asRuntimeException (ex->status Status/INTERNAL err "Internal error. Please try again later."))
      (.asRuntimeException st))))

(defn list-accounts [^ListAccountsRequest _request ^StreamObserver responseObserver]
  (-> (d/let-flow [reply-pb (map->proto ListAccountsReply
                                        {})]
                  (.onNext responseObserver reply-pb)
                  (.onCompleted responseObserver))
      (d/catch #(.onError responseObserver (return-error %)))))

(defn create-service []
  (fpb/accounts-impl {:list-accounts #'list-accounts}))
