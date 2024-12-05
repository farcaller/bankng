(ns bankng.codesender.core
  (:require [bankng.kvstore.ifc :as kvstore]
            [bankng.pb-mucklet.ifc :as mu]
            [bankng.proto-tools.ifc :as p])
  (:import [io.grpc Status]))

(defn generate-code []
  (rand-int 10000))

(defn code-key [session-id]
  (str "otp-code-" session-id))

(def code-ttl (* 60 5))

(defn send-code
  [session-id char-id]
  (let [code (generate-code)
        code-str (format "%04d" code)]
    (kvstore/store (code-key session-id) {:char-id char-id :code code-str} code-ttl)
    (mu/send-message char-id
                     (str "Your one time login code is `" code-str "`. "
                          "Only use it to log in to Dracones Financial Services. "
                          "**Do not** share this code with anyone."))))

(defn verify-code
  [session-id code]
  (let [session-key (code-key session-id)
        session-info (kvstore/get session-key)
        char-id (:char-id session-info)
        session-code (:code session-info)]
    (when-not session-info
      (throw (p/->err Status/NOT_FOUND "session not found")))
    (when-not (= code session-code)
      (throw (p/->err Status/INVALID_ARGUMENT "code incorrect")))
    (kvstore/del session-key)
    char-id))
