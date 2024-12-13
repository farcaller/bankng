(ns bankng.accounts.ifc
  (:require [bankng.accounts.core :as core]))

(defn list-accounts [char-id]
  (core/list-accounts char-id))
