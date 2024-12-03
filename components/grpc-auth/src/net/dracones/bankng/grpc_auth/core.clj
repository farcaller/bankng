(ns net.dracones.bankng.grpc-auth.core
  (:require [clojure.string :as str]
            [net.dracones.bankng.proto-tools.interface :as p])
  (:import [io.grpc ServerInterceptor ServerCall ServerCallHandler Metadata Metadata$Key Context Contexts ServerCall$Listener]
           [com.google.protobuf Descriptors$MethodDescriptor]
           [net.dracones.bankng FirstFactorReply SecondFactorReply AccountsOuterClass]))

(defonce jwt-sub (Context/key "jwt-sub"))

(defonce authorization-key (Metadata$Key/of "authorization" Metadata/ASCII_STRING_MARSHALLER))

(defn fail-with [call status msg]
  (.close call (.withDescription status msg) (Metadata.))
  (ServerCall$Listener.))

(defn auth-interceptor
  "Returns a gRPC interceptor that verifies the JWT and extracts the client id."
  []
  (reify ServerInterceptor
    (^ServerCall$Listener
     interceptCall
     [_this ^ServerCall call ^Metadata metadata ^ServerCallHandler next]
      (tap> {:call call :headers metadata :next next})
     (let [requires-auth? (-> call
                              .getMethodDescriptor
                              .getSchemaDescriptor
                              .getMethodDescriptor
                              .getOptions
                              (.getExtension AccountsOuterClass/requiresAuth))
           ctx (-> (Context/current) (.withValue jwt-sub ""))]
       (if requires-auth?
         (let [authorization (.get metadata authorization-key)
               [kind token] (str/split authorization #" ")]
           (if (not= kind "Bearer")
             (fail-with call p/UNAUTHENTICATED "incorrect token type")
             
             (.startCall next call metadata)))
         (.startCall next call metadata))))))


;; (let [method-descriptor (-> call .getMethodDescriptor .getSchemaDescriptor)
      ;;       requires-auth (some-> method-descriptor
      ;;                             (.getOptions)
      ;;                             (.getExtension MyProto/RequiresAuth))]
      ;;   (when requires-auth
      ;;     (let [client-ip (.get headers (Metadata/Key/of "x-forwarded-for" Metadata/ASCII_STRING_MARSHALLER))]
      ;;       (println "Authenticated request from IP:" client-ip)))
      ;;   (.startCall next call headers))

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
      (.getExtension AccountsOuterClass/requiresAuth)
      )
  :rcf)
