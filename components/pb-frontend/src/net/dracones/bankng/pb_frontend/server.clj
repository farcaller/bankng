(ns net.dracones.bankng.pb-frontend.server
  (:import [io.grpc Grpc InsecureServerCredentials Status]
           [net.dracones.bankng AuthGrpc$AuthImplBase FirstFactorReply SecondFactorReply]))

(defn auth-impl
  [on-first-factor on-second-factor]
  (proxy [AuthGrpc$AuthImplBase] []
    (firstFactor [request responseObserver]
      (on-first-factor request responseObserver))

    (secondFactor [request responseObserver]
      (on-second-factor request responseObserver))))
