(ns bankng.accounts.ifc
  (:require [bankng.accounts.core :as core]))

(defn list-accounts [char-id]
  (core/list-accounts char-id))

(defn list-transactions
  "Lists the transactions for the given customer and account-id. Optionally
   takes a limit and an offset-ts, where the limit is a number of transactions
   to return and the offset-ts is a timestamp of the transaction to start from.
   
   Returns a list of transactions, where each transaction is a map with the
   keys:
   - transaction/from: account eid
   - transaction/to: account eid
   - transaction/amount: amount of the transaction
   - transaction/time: timestamp of the transaction
   - transaction/direction: :debit or :credit
   - transaction/correspondent: correspondent eid"
  [customer-id account-id & {:keys [limit offset-ts] :or {limit 10 offset-ts nil}}]
  (core/list-transactions customer-id account-id :limit limit :offset-ts offset-ts))
