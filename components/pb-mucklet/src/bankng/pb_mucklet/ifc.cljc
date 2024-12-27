(ns bankng.pb-mucklet.ifc
  #?(:clj (:require [bankng.pb-mucklet.client :as client])
     :cljs (:require [bankng.pb-mucklet.server :as server])))

#?(:clj
   (do
     (defn lookup-character
       [& {:keys [full-name char-id] :as opts}]
       (client/lookup-character opts))

     (defn send-message
       [char-id msg]
       (client/send-message char-id msg))))

#?(:cljs
   (do
     (def mucklet-service server/mucklet-service)
     (def LookupCharacterRequest server/LookupCharacterRequest)
     (def LookupCharacterReply server/LookupCharacterReply)
     (def SendMessageRequest server/SendMessageRequest)
     (def SendMessageReply server/SendMessageReply)))
