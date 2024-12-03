(ns net.dracones.bankng.grpc-auth.interface-test
  (:require [clojure.test :as test :refer :all]
            [net.dracones.bankng.grpc-auth.interface :as grpc-auth])
  (:import [io.grpc.inprocess InProcessServerBuilder InProcessChannelBuilder]))

(def ^:dynamic *chan* nil)

(defn grpc-server [f]
  (let [unique-name (InProcessServerBuilder/generateName)
        server (-> (InProcessServerBuilder/forName unique-name)
                   .directExecutor
                   #_(.addService svc)
                   (.intercept (grpc-auth/auth-interceptor))
                   .build
                   .start)
        chan (-> (InProcessChannelBuilder/forName unique-name)
                 .directExecutor
                 .build)]
    (binding [*chan* chan]
      (f)
      (.shutdown server))))

(use-fixtures :each grpc-server)

(deftest dummy-test
  (is (= 1 1)))
