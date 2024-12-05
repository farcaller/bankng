(ns bankng.mucklet-proxy.bot
  (:require [kitchen-async.promise :as p]
            [botmaster.state :as state]
            [oops.core :refer [oget]]))

(def mucklet-fileserver-host (oget js/process "env.MUCKLET_FILESERVER_HOST"))

(defn lookup-character
  "Looks up a character by full name. Returns a promise that resolves to a character object."
  [full-name]
  (p/let [client (state/get-client :scambanker)
          bot (.call client "core" "getBot")
          char (.call bot "getChar" #js {:charName full-name})
          char (js->clj (.-props ^js char) :keywordize-keys true)]
    {:first_name (:name char)
     :last_name (:surname char)
     :pfp_url (str mucklet-fileserver-host "/core/char/avatar/" (:avatar char) "?thumb=l")
     :char_id (:id char)}))

(defn send-message
  "Sends a message to a character. Returns a promise that resolves or throws."
  [char-id message]
  (p/let [ctrl (state/get-ctrl :scambanker)]
    (.call ctrl "message" #js {:charIds #js [char-id] :msg message})))

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
