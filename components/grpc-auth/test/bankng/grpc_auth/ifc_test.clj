(ns bankng.grpc-auth.ifc-test
  (:require [clojure.test :as test :refer :all]
            [mount.core :as mount]
            [bankng.pb-frontend.ifc :as fpb]
            [bankng.grpc-auth.ifc :as grpc-auth]
            [bankng.jwt.ifc :as jwt])
  (:import [io.grpc.inprocess InProcessServerBuilder InProcessChannelBuilder]
           [bankng AccountsGrpc ListAccountsReply Account]
           [io.grpc StatusRuntimeException ClientInterceptor Metadata Metadata$Key
            ClientInterceptors ForwardingClientCall$SimpleForwardingClientCall
            Context]))

(def ^:dynamic *chan* nil)

(defn empty-handler [_request responseObserver]
  (let [sub-value (-> (.get grpc-auth/jwt-sub-claim-key (Context/current)))]
    (.onNext responseObserver (-> (ListAccountsReply/newBuilder)
                                  (.addAccounts (-> (Account/newBuilder)
                                                    (.setName sub-value)
                                                    .build))
                                  .build)))
  (.onCompleted responseObserver))

(defn header-client-interceptor [value]
  (reify ClientInterceptor
    (interceptCall [_ method call-options next]
      (proxy [ForwardingClientCall$SimpleForwardingClientCall] [(.newCall next method call-options)]
        (start [response-listener headers]
          (let [key (Metadata$Key/of "authorization" Metadata/ASCII_STRING_MARSHALLER)]
            (.put headers key value))
          (proxy-super start response-listener headers))))))

(defn grpc-server [f]
  (let [unique-name (InProcessServerBuilder/generateName)
        service (fpb/accounts-impl {:list-accounts #'empty-handler})
        server (-> (InProcessServerBuilder/forName unique-name)
                   .directExecutor
                   (.addService service)
                   (.intercept (grpc-auth/auth-interceptor))
                   .build
                   .start)
        chan (-> (InProcessChannelBuilder/forName unique-name)
                 .directExecutor
                 .build)]
    (binding [*chan* chan]
      (f)
      (.shutdown server))))

(defn with-mount [f]
  #_{:clj-kondo/ignore [:unresolved-namespace]}
  (mount/start-with {#'bankng.config.ifc/config
                     {:jwt {:private-key "./development/resources/jwtkey.pem"
                            :public-key "./development/resources/jwtkey.pub"
                            :issuer "https://auth.dracones.net"
                            :audience "https://bank.dracones.net"
                            :ttl 2}}})
  (f)
  (mount/stop))

(use-fixtures :each with-mount grpc-server)

(deftest fail-when-no-header
  (let [stub (AccountsGrpc/newBlockingStub *chan*)]
    (is (thrown-with-msg? StatusRuntimeException #"^UNAUTHENTICATED: missing authorization header$" (.listAccounts stub nil)))))

(deftest fail-when-no-bearer
  (let [intercepted-chan (ClientInterceptors/intercept *chan* [(header-client-interceptor "Basic abc")])
        stub (AccountsGrpc/newBlockingStub intercepted-chan)]
    (is (thrown-with-msg? StatusRuntimeException #"^UNAUTHENTICATED: unsupported authorization type$" (.listAccounts stub nil)))))

(deftest fail-when-jwt-bad
  (let [intercepted-chan (ClientInterceptors/intercept *chan* [(header-client-interceptor "Bearer a.b.c")])
        stub (AccountsGrpc/newBlockingStub intercepted-chan)]
    (is (thrown-with-msg? StatusRuntimeException #"^UNAUTHENTICATED: invalid token$" (.listAccounts stub nil)))))

(deftest fail-when-jwt-expired
  (let [token (jwt/create-jwt "my-id" {:exp 0})
        intercepted-chan (ClientInterceptors/intercept *chan* [(header-client-interceptor (str "Bearer " token))])
        stub (AccountsGrpc/newBlockingStub intercepted-chan)]
    (is (thrown-with-msg? StatusRuntimeException #"^UNAUTHENTICATED: invalid token$" (.listAccounts stub nil)))))

(deftest succeed
  (let [token (jwt/create-jwt "my-id")
        intercepted-chan (ClientInterceptors/intercept *chan* [(header-client-interceptor (str "Bearer " token))])
        stub (AccountsGrpc/newBlockingStub intercepted-chan)
        reply (.listAccounts stub nil)]
    (is (= "my-id" (-> reply (.getAccounts 0) .getName)))))
