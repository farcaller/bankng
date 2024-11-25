(ns net.dracones.bankng.frontend-service.core
  (:require [net.dracones.bankng.pb-frontend.interface :as fpb]
            [net.dracones.bankng.proto-tools.interface :refer [map->proto proto->map ->statusex]]
            [net.dracones.bankng.pb-mucklet.interface :as mu]
            [net.dracones.bankng.codesender.interface :as co]
            [manifold.deferred :as d])
  (:import [net.dracones.bankng FirstFactorReply SecondFactorReply]))

(defn on-first-factor [request responseObserver]
  (-> (d/let-flow [request (proto->map request)
                   full-name (:full-name request)
                   char (mu/lookup-character full-name)
                   reply-pb (map->proto FirstFactorReply
                                        {:first-name (:first-name char)
                                         :pfp-url (:pfp-url char)})
                   _ (co/send-code (:char-id char))]
                  (.onNext responseObserver reply-pb)
                  (.onCompleted responseObserver))
      (d/catch #(.onError responseObserver (->statusex %)))))

(defn on-second-factor [request responseObserver])

(defn create-service [] (fpb/auth-impl #'on-first-factor #'on-second-factor))
