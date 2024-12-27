(ns bankng.mucklet-proxy.bot
  (:require [kitchen-async.promise :as p]
            [botmaster.state :as state]
            [botmaster.trace :refer [res-call res-client-call]]
            [oops.core :refer [oget]]
            [goog.object :as gobj]))

(def mucklet-fileserver-host (oget js/process "env.MUCKLET_FILESERVER_HOST"))

(defn persist-char-in-cache!
  "Persists a character in the RES cache unless it already has a direct subscription."
  [^js client ^js char-res]
  (let [^js cache (.-cache client)
        ^js ci (gobj/get cache (.-_rid char-res))]
    (when (and ci (= (.-direct ci) 0))
      (.log js/console "Retaining character in cache" (.-_rid char-res))
      (.on char-res))))

(defn lookup-character
  "Looks up a character by full name. Returns a promise that resolves to a character object."
  [& {:keys [full-name char-id]}]
  (when-not (or full-name char-id)
    (throw (ex-info "Either full-name or char-id must be provided" {:full-name full-name :char-id char-id})))
  (when (and full-name char-id)
    (throw (ex-info "Only one of full-name or char-id must be provided" {:full-name full-name :char-id char-id})))
  (p/let [client (state/get-client :scambanker)
          bot (res-client-call client "core" "getBot")
          by-id (some? char-id)
          ^js char-res (res-call bot "getChar" (if by-id {:charId char-id} {:charName full-name}))
          char (js->clj (.-props char-res) :keywordize-keys true)]
    (persist-char-in-cache! client char-res)
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
