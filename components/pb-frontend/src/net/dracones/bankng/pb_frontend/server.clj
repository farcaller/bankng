(ns net.dracones.bankng.pb-frontend.server
  (:import [net.dracones.bankng AuthGrpc$AuthImplBase AccountsGrpc$AccountsImplBase]))

(defn resolve-fn [f]
  (if (var? f) @f f))

(defn auth-impl
  [on-first-factor on-second-factor]
  (proxy [AuthGrpc$AuthImplBase] []
    (firstFactor [request responseObserver]
      ((resolve-fn on-first-factor) request responseObserver))

    (secondFactor [request responseObserver]
      ((resolve-fn on-second-factor) request responseObserver))))

(defn accounts-impl
  [{:keys [list-accounts list-transactions]}]
  (proxy [AccountsGrpc$AccountsImplBase] []
    (listAccounts [request responseObserver]
      ((resolve-fn list-accounts) request responseObserver))

    (listTransactions [request responseObserver]
      ((resolve-fn list-transactions) request responseObserver))))
