(ns bankng.mucklet-service.core
  (:require ["@grpc/grpc-js" :as grpc]
            [bankng.mucklet-bot.ifc :as bot]
            [kitchen-async.promise :as p]
            [bankng.pb-mucklet.ifc :refer [LookupCharacterRequest LookupCharacterReply]]
            ["google-protobuf/google/protobuf/empty_pb.js" :refer [Empty]]))

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
  (let [req (.-request call)]
    (p/then (condp = (.getByCase req)
              (-> LookupCharacterRequest .-ByCase .-FULL_NAME) (bot/lookup-character :full-name (.getFullName req))
              (-> LookupCharacterRequest .-ByCase .-CHAR_ID) (bot/lookup-character :char-id (.getCharId req))
              :else (throw (ex-info "invalid lookup type" {:req req})))
            #(callback nil (-> (LookupCharacterReply.)
                               (.setFirstName (:first_name %))
                               (.setLastName (:last_name %))
                               (.setPfpUrl (:pfp_url %))
                               (.setCharId (:char_id %))))
            #(callback (err->status %) nil))))

(defn send-message
  [^js call ^js callback]
  (let [req (.-request call)]
    (p/then (bot/send-message (.getCharId req) (.getMessage req))
            #(callback nil (Empty.))
            #(callback (err->status %) nil))))
