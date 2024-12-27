(ns bankng.pb-mucklet.server
  (:require ["./gen/mucklet_pb" :as mpb]
            ["./gen/mucklet_grpc_pb" :as gmpb]))

(def mucklet-service gmpb/MuckletService)

(def LookupCharacterRequest mpb/LookupCharacterRequest)
(def LookupCharacterReply mpb/LookupCharacterReply)
(def SendMessageRequest mpb/SendMessageRequest)
(def SendMessageReply mpb/SendMessageReply)
