(ns net.dracones.bankng.pb-frontend.server
  (:import [io.grpc.stub StreamObserver]
   [net.dracones.bankng ListAccountsRequest ListTransactionsRequest AuthGrpc$AuthImplBase AccountsGrpc$AccountsImplBase]))

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
    (listAccounts [^ListAccountsRequest request ^StreamObserver responseObserver]
      ((resolve-fn list-accounts) request responseObserver))

    (listTransactions [^ListTransactionsRequest request ^StreamObserver responseObserver]
      ((resolve-fn list-transactions) request responseObserver))))
