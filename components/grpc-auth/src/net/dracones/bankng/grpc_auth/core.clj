(ns net.dracones.bankng.grpc-auth.core
  (:require [clojure.string :as str]
            [net.dracones.bankng.proto-tools.interface :as p :refer [throw-status!]]
            [net.dracones.bankng.jwt.interface :as jwt])
  (:import [io.grpc ServerInterceptor ServerCall ServerCallHandler Metadata Metadata$Key
            Context Contexts ServerCall$Listener Status]
           [net.dracones.bankng AccountsOuterClass]))

(defonce authorization-key (Metadata$Key/of "authorization" Metadata/ASCII_STRING_MARSHALLER))

(defonce jwt-sub-claim-key (Context/key "jwt-sub-claim"))

(defn fail-with [call status msg]
  (.close call (.withDescription status msg) (Metadata.))
  (proxy [ServerCall$Listener] []))

(defn auth-interceptor
  "Returns a gRPC interceptor that verifies the JWT and extracts the client id."
  []
  (reify ServerInterceptor
    (^ServerCall$Listener
      interceptCall
      [_this ^ServerCall call ^Metadata metadata ^ServerCallHandler next]
      (try
        (let [requires-auth? (-> call
                                 .getMethodDescriptor
                                 .getSchemaDescriptor
                                 .getMethodDescriptor
                                 .getOptions
                                 (.getExtension AccountsOuterClass/requiresAuth))]
          (if requires-auth?
            (let [authorization (.get metadata authorization-key)
                  _ (when-not authorization (throw-status! Status/UNAUTHENTICATED "missing authorization header"))
                  [kind token] (when authorization (str/split authorization #" "))
                  _ (when-not (= kind "Bearer") (throw-status! Status/UNAUTHENTICATED "unsupported authorization type"))
                  claims (try (jwt/validate-jwt token)
                              (catch Exception e
                                (throw-status! Status/UNAUTHENTICATED "invalid token")))
                  sub (:sub claims)
                  _ (when-not sub (throw-status! Status/UNAUTHENTICATED "invalid token"))
                  ctx (-> (Context/current) (.withValue jwt-sub-claim-key sub))]
              (Contexts/interceptCall ctx call metadata next))
            (.startCall next call metadata)))
        (catch Exception e
          (.close call (Status/fromThrowable e) (Metadata.))
          (proxy [ServerCall$Listener] []))))))

(comment
  (let [[kind token] (str/split "Bearer safsagf asgg sasg" #" ")]
    {:k kind :t token})
  (->
   (.get (Metadata$Key/of "authorization" Metadata/ASCII_STRING_MARSHALLER)))
  (->
   .getMethodDescriptor
   .getSchemaDescriptor
   .getMethodDescriptor
   .getOptions
   (.getExtension AccountsOuterClass/requiresAuth))
  :rcf)
