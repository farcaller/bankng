(ns net.dracones.bankng.pb-frontend.server
  (:import [io.grpc Grpc InsecureServerCredentials Status]
           [net.dracones.bankng AuthGrpc$AuthImplBase FirstFactorReply SecondFactorReply]))

(defn resolve-fn [f]
  (if (var? f) @f f))

(defn auth-impl
  [on-first-factor on-second-factor]
  (proxy [AuthGrpc$AuthImplBase] []
    (firstFactor [request responseObserver]
      ((resolve-fn on-first-factor) request responseObserver))

    (secondFactor [request responseObserver]
      ((resolve-fn on-second-factor) request responseObserver))))
