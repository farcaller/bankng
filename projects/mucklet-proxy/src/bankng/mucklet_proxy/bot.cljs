(ns bankng.mucklet-proxy.bot
  (:require [kitchen-async.promise :as p]
            [botmaster.state :as state]
            [botmaster.trace :refer [res-call res-client-call]]
            [oops.core :refer [oget]]))

(def mucklet-fileserver-host (oget js/process "env.MUCKLET_FILESERVER_HOST"))

(defn lookup-character
  "Looks up a character by full name. Returns a promise that resolves to a character object."
  [& {:keys [full-name char-id]}]
  (when-not (or full-name char-id)
    (throw (ex-info "Either full-name or char-id must be provided" {:full-name full-name :char-id char-id})))
  (when (and full-name char-id)
    (throw (ex-info "Only one of full-name or char-id must be provided" {:full-name full-name :char-id char-id})))
  (p/let [client (state/get-client :scambanker)
          bot (res-client-call client "core" "getBot")
          char (res-call bot "getChar" (if full-name
                                         {:charName full-name}
                                         {:charId char-id}))
          char (js->clj (.-props ^js char) :keywordize-keys true)]
    {:first_name (:name char)
     :last_name (:surname char)
     :pfp_url (str mucklet-fileserver-host "/core/char/avatar/" (:avatar char) "?thumb=l")
     :char_id (:id char)}))

(defn send-message
  "Sends a message to a character. Returns a promise that resolves or throws."
  [char-id message]
  (p/let [ctrl (state/get-ctrl :scambanker)]
    (res-call ctrl "message" {:charIds #js [char-id] :msg message})))

(comment
  (def client (state/get-client :scambanker))
  (-> (.call client "core" "getBot")
      (.then #(def bot %)))

  (-> #_(.call bot "getChar" #js {:charName "Birb whatever"})
   (lookup-character "Birb crowley")
      (.then #(println "res" %))
      (.catch #(println "err" %)))

  (send-message "cajd55e9gbrqf703lcve" "test")
  :rcf)
