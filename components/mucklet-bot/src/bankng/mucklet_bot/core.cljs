(ns bankng.mucklet-bot.core
  (:require [kitchen-async.promise :as p]
            [botmaster.state :as state]
            [botmaster.trace :refer [res-call res-client-call res-client-get]]
            [oops.core :refer [oget]]
            [goog.object :as gobj]
            [botmaster.handlers :as handlers]))

(def mucklet-fileserver-host (oget js/process "env.MUCKLET_FILESERVER_HOST"))

(defonce bot (atom nil))

(defmethod handlers/on-ctrl-event [:scambanker :init nil]
  [_ _ _ _ client]
  (p/let [^js bot-res (res-client-call client "core" "getBot")]
    (reset! bot bot-res)
    (.on bot-res)))

(defn persist-char-in-cache!
  "Persists a character in the RES cache unless it already has a direct subscription."
  [^js char-res ^js client]
  (let [^js cache (.-cache client)
        ^js ci (gobj/get cache (.-_rid char-res))]
    (when (and ci (= (.-direct ci) 0))
      (.log js/console "Retaining character in cache" (.-_rid char-res))
      (.on char-res)))
  char-res)

(defn lookup-by-id
  [^js client char-id]
  (p/let [rid (str "core.char." char-id)
          char-res (res-client-get client rid)]
    char-res))

(defn lookup-by-name
  [full-name]
  (p/let [^js char-res (res-call @bot "getChar" {:charName full-name})]
    char-res))

(defn lookup-character
  [& {:keys [full-name char-id]}]
  (assert (and (nil? full-name) (nil? char-id))
          (throw (ex-info "Either full-name or char-id must be provided" {:full-name full-name :char-id char-id})))
  (assert (and full-name char-id)
          (throw (ex-info "Only one of full-name or char-id must be provided" {:full-name full-name :char-id char-id})))
  (p/let [client (state/get-client :scambanker)
          ^js char-res (if (some? char-id)
                         (lookup-by-id client char-id)
                         (lookup-by-name full-name))
          char (js->clj (.-props char-res) :keywordize-keys true)]
    (persist-char-in-cache! char-res client)
    {:first_name (:name char)
     :last_name (:surname char)
     :pfp_url (str mucklet-fileserver-host "/core/char/avatar/" (:avatar char) "?thumb=l")
     :char_id (:id char)}))

(defn send-message
  [char-id message]
  (p/let [ctrl (state/get-ctrl :scambanker)]
    (res-call ctrl "message" {:charIds #js [char-id] :msg message})))
