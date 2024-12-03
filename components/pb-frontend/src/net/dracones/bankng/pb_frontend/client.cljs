(ns net.dracones.bankng.pb-frontend.client
  (:require ["../../../../gen/js/auth_pb.js" :as aupb]
            ["../../../../gen/js/auth_grpc_web_pb.js" :as grpcAupb]
            ["../../../../gen/js/accounts_pb.js" :as acpb]
            ["../../../../gen/js/accounts_grpc_web_pb.js" :as grpcAcpb]
            [kitchen-async.promise :as p]))

(goog-define GRPC_API_FRONTEND "")

(defn new-auth-client
  [url]
  (grpcAupb/AuthPromiseClient. url))

(defonce auth-client (new-auth-client GRPC_API_FRONTEND))

(defn new-accounts-client
  [url]
  (grpcAcpb/AccountsPromiseClient. url))

(defonce accounts-client (new-accounts-client GRPC_API_FRONTEND))


(defn first-factor
  [full-name]
  (p/let [req (-> (aupb/FirstFactorRequest.)
                  (.setFullName full-name))
          rep (.firstFactor auth-client req)
          rep (js->clj (.toObject rep js/Object) :keywordize-keys true)]
    rep))

(defn second-factor
  [session-id code]
  (p/let [req (-> (aupb/SecondFactorRequest.)
                  (.setSessionId session-id)
                  (.setCode code))
          rep (.secondFactor auth-client req)
          rep (js->clj (.toObject rep js/Object) :keywordize-keys true)]
    rep))

(defn list-accounts
  [jwt]
  (p/let [req (-> (acpb/ListAccountsRequest.))
          rep (.listAccounts accounts-client req #js {:authorization (str "Bearer " jwt)})
          rep (js->clj (.toObject rep js/Object) :keywordize-keys true)]
    rep))

(comment
  (-> (list-accounts "test")
      (p/then #(println %))
      (p/catch* #(println %)))
  :rcf)
