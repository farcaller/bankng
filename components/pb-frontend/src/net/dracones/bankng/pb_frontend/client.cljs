(ns net.dracones.bankng.pb-frontend.client
  (:require ["../../../../gen/js/auth_pb.js" :as Pb]
            ["../../../../gen/js/auth_grpc_web_pb.js" :as GrpcPb]
            [kitchen-async.promise :as p]))

(goog-define GRPC_API_FRONTEND "")

(defn new-client
  [url]
  (GrpcPb/AuthPromiseClient. url))

(defonce client (new-client GRPC_API_FRONTEND))

(defn first-factor
  [full-name]
  (p/let [req (-> (Pb/FirstFactorRequest.)
                  (.setFullName full-name))
          rep (.firstFactor client req)
          rep (js->clj (.toObject rep js/Object) :keywordize-keys true)]
    rep))

