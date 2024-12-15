(ns bankng.frontend-service.core
  (:require [bankng.pb-frontend.ifc :as fpb]
            [bankng.proto-tools.ifc :refer [map->proto proto->map ->status code? ex->status]]
            [bankng.pb-mucklet.ifc :as mu]
            [bankng.codesender.ifc :as co]
            [bankng.jwt.ifc :as jwt]
            [taoensso.telemere :as log]
            [manifold.deferred :as d])
  (:import [io.grpc Status]
           [io.grpc.stub StreamObserver]
           [bankng FirstFactorRequest FirstFactorReply SecondFactorRequest SecondFactorReply]))

(defn return-error [err]
  (log/error! err)
  (let [st (->status err)
        code (code? st)]
    (if (= :UNKNOWN code)
      (.asRuntimeException (ex->status Status/INTERNAL err "Internal error. Please try again later."))
      (.asRuntimeException st))))

(defn on-first-factor [^FirstFactorRequest request ^StreamObserver responseObserver]
  (try (let [request (proto->map request)
             full-name (:full-name request)
             char (mu/lookup-character full-name)
             session-id (str (random-uuid))
             reply-pb (map->proto FirstFactorReply
                                  {:full-name (str (:first-name char) " " (:last-name char))
                                   :first-name (:first-name char)
                                   :pfp-url (:pfp-url char)
                                   :session-id session-id})]
         (co/send-code session-id (:char-id char))
         (.onNext responseObserver reply-pb)
         (.onCompleted responseObserver))
      (catch Exception e (.onError responseObserver (return-error e)))))

(defn on-second-factor [^SecondFactorRequest request ^StreamObserver responseObserver]
  (try (let [request (proto->map request)
                   code (:code request)
                   session-id (:session-id request)
                   char-id (co/verify-code session-id code)
                   jwt-str (jwt/create-jwt char-id)
                   reply-pb (map->proto SecondFactorReply
                                        {:jwt jwt-str})]
                  (.onNext responseObserver reply-pb)
                  (.onCompleted responseObserver))
      (catch Exception e (.onError responseObserver (return-error e)))))

(defn create-service [] (fpb/auth-impl #'on-first-factor #'on-second-factor))
