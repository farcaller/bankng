(ns net.dracones.bankng.frontend-service.core
  (:require [net.dracones.bankng.pb-frontend.interface :as fpb]
            [net.dracones.bankng.proto-tools.interface :refer [map->proto proto->map ->status code? ex->status INTERNAL]]
            [net.dracones.bankng.pb-mucklet.interface :as mu]
            [net.dracones.bankng.codesender.interface :as co]
            [net.dracones.bankng.jwt.interface :as jwt]
            [taoensso.telemere :as log]
            [manifold.deferred :as d])
  (:import [net.dracones.bankng FirstFactorReply SecondFactorReply]))

(defn return-error [err]
  (log/error! err)
  (let [st (->status err)
        code (code? st)]
    (if (= :UNKNOWN code)
      (.asRuntimeException (ex->status INTERNAL err "Internal error. Please try again later."))
      (.asRuntimeException st))))

(defn on-first-factor [request responseObserver]
  (-> (d/let-flow [request (proto->map request)
                   full-name (:full-name request)
                   char (mu/lookup-character full-name)
                   session-id (str (random-uuid))
                   reply-pb (map->proto FirstFactorReply
                                        {:first-name (:first-name char)
                                         :pfp-url (:pfp-url char)
                                         :session-id session-id})
                   _ (co/send-code session-id (:char-id char))]
                  (.onNext responseObserver reply-pb)
                  (.onCompleted responseObserver))
      (d/catch #(.onError responseObserver (return-error %)))))

(defn on-second-factor [request responseObserver]
  (-> (d/let-flow [request (proto->map request)
                   code (:code request)
                   session-id (:session-id request)
                   char-id (co/verify-code session-id code)
                   jwt-str (jwt/create-jwt char-id)
                   reply-pb (map->proto SecondFactorReply
                                        {:jwt jwt-str})]
                  (.onNext responseObserver reply-pb)
                  (.onCompleted responseObserver))
      (d/catch #(.onError responseObserver (return-error %)))))

(defn create-service [] (fpb/auth-impl #'on-first-factor #'on-second-factor))
