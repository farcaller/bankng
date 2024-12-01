(ns net.dracones.bankng.codesender.core
  (:require [net.dracones.bankng.kvstore.interface :as kvstore]
            [net.dracones.bankng.pb-mucklet.interface :as mu]))

(defn generate-code []
  (rand-int 10000))

(defn code-key [char-id]
  (str "otp-code-" char-id))

(def code-ttl (* 60 5))

(defn send-code [char-id]
  (let [code (generate-code)]
    (kvstore/store (code-key char-id) code code-ttl)
    (mu/send-message char-id
                     (str "Your one time login code is `" code "`. "
                          "Only use it to log in to Dracones Financial Services. "
                          "**Do not** share this code with anyone."))))
