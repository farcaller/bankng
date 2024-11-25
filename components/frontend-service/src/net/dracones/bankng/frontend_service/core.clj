(ns net.dracones.bankng.frontend-service.core
  (:require [net.dracones.bankng.pb-frontend.interface :as fpb]
            [net.dracones.bankng.proto-tools.interface :refer [map->proto proto->map]])
  (:import [net.dracones.bankng FirstFactorReply SecondFactorReply]))

(defn on-first-factor [request responseObserver]
  (let [reply-pb (map->proto FirstFactorReply
                             {:first-name ""
                              :pfp-url ""})]
    (.onNext responseObserver reply-pb)
    (.onCompleted responseObserver)))

(defn on-second-factor [request responseObserver])

(defn create-service [] (fpb/auth-impl #'on-first-factor #'on-second-factor))
