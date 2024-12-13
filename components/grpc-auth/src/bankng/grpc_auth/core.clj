(ns bankng.grpc-auth.core
  (:require [clojure.string :as str]
            [bankng.proto-tools.ifc :as p :refer [throw-status!]]
            [bankng.jwt.ifc :as jwt])
  (:import [io.grpc ServerInterceptor ServerCall ServerCallHandler Metadata Metadata$Key
            Context Contexts ServerCall$Listener Status Context$Key]
           [io.grpc.protobuf ProtoMethodDescriptorSupplier]
           [bankng AccountsOuterClass]))

(defonce authorization-key (Metadata$Key/of "authorization" Metadata/ASCII_STRING_MARSHALLER))

(defonce ^Context$Key jwt-sub-claim-key (Context/key "jwt-sub-claim"))

(defn fail-with [^ServerCall call ^Status status msg]
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
                                 ^ProtoMethodDescriptorSupplier identity
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
