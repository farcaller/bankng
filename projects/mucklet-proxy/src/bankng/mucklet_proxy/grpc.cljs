(ns bankng.mucklet-proxy.grpc
  (:require ["@grpc/proto-loader" :as protoLoader]
            ["@grpc/grpc-js" :as grpc]
            [bankng.mucklet-proxy.bot :as bot]
            [kitchen-async.promise :as p]
            [oops.core :refer [oget]]))

(def PROTO_PATH (oget js/process "env.PROTO_PATH"))

(defonce package-def (.loadSync protoLoader
                                PROTO_PATH
                                #js {:keepCase true
                                     :longs js/String
                                     :enums js/String
                                     :defaults true
                                     :oneofs true}))

(defonce proto-descriptor (.loadPackageDefinition grpc package-def))

(defn err->status
  [err]
  (condp = (.-code err)
    "core.namedCharNotAwake"
    #js {:code grpc/status.FAILED_PRECONDITION
         :message "character asleep"}
    "core.charNotFound"
    #js {:code grpc/status.NOT_FOUND
         :message "character not found"}
    #js{:code grpc/status.UNKNOWN
        :message (.-message err)}))

(defn lookup-character
  [^js call ^js callback]
  (let [full-name (-> call .-request .-full_name)]
    (p/then (bot/lookup-character full-name)
            #(callback nil (clj->js %))
            #(callback (err->status %) nil))))

(defn send-message
  [^js call ^js callback]
  (p/then (bot/send-message (-> call .-request .-char_id)
                            (-> call .-request .-message))
          #(callback nil #js{})
          #(callback (err->status %) nil)))

(def server (let [s (grpc/Server.)]
              (.addService
               s
               (oget proto-descriptor "bankng.mucklet.Mucklet.service")
               #js {:lookupCharacter #(lookup-character %1 %2)
                    :sendMessage #(send-message %1 %2)})
              s))

(defn start
  [address]
  (.bindAsync server address (grpc/ServerCredentials.createInsecure) #(println "server running at" address)))

(defn stop
  []
  (.tryShutdown server #()))

(comment
  (start "127.0.0.1:8081")
  (stop)

  :rcf)
