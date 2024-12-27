(ns bankng.pb-frontend.client
  (:require ["./gen/auth_pb.js" :as apb]
            ["./gen/auth_grpc_web_pb.js" :as gapb]
            ["./gen/bankng_pb.js" :as bpb]
            ["./gen/bankng_grpc_web_pb.js" :as gbpb]
            [kitchen-async.promise :as p]))

(goog-define GRPC_API_FRONTEND "")

(defn new-auth-client
  [url]
  (gapb/AuthPromiseClient. url))

(defonce auth-client (new-auth-client GRPC_API_FRONTEND))

(defn new-accounts-client
  [url]
  (gbpb/AccountsPromiseClient. url))

(defonce accounts-client (new-accounts-client GRPC_API_FRONTEND))


(defn first-factor
  [full-name]
  (p/let [req (-> (apb/FirstFactorRequest.)
                  (.setFullName full-name))
          rep (.firstFactor auth-client req)
          rep (js->clj (.toObject rep js/Object) :keywordize-keys true)]
    rep))

(defn second-factor
  [session-id code]
  (p/let [req (-> (apb/SecondFactorRequest.)
                  (.setSessionId session-id)
                  (.setCode code))
          rep (.secondFactor auth-client req)
          rep (js->clj (.toObject rep js/Object) :keywordize-keys true)]
    rep))

(defn list-accounts
  [jwt]
  (p/let [req (-> (bpb/ListAccountsRequest.))
          rep (.listAccounts accounts-client req #js {:authorization (str "Bearer " jwt)})
          rep (js->clj (.toObject rep js/Object) :keywordize-keys true)]
    rep))

(defn list-transactions
  [jwt iban]
  (p/let [req (-> (bpb/ListTransactionsRequest.)
                  (.setIban iban))
          rep (.listTransactions accounts-client req #js {:authorization (str "Bearer " jwt)})
          rep (js->clj (.toObject rep js/Object) :keywordize-keys true)]
    rep))
