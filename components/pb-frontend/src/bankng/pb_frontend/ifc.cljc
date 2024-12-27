(ns bankng.pb-frontend.ifc
  #?(:cljs (:require [bankng.pb-frontend.client :as client])
     :clj (:require [bankng.pb-frontend.server :as server])))

#?(:cljs
   (do
     (defn first-factor [full-name]
       (client/first-factor full-name))
     
     (defn second-factor [session-id code]
       (client/second-factor session-id code))
     
     (defn list-accounts [jwt]
       (client/list-accounts jwt))
     
     (defn list-transactions [jwt iban]
       (client/list-transactions jwt iban))))

#?(:clj
   (do (defn auth-impl
         [on-first-factor on-second-factor]
         (server/auth-impl on-first-factor on-second-factor))
       
       (defn accounts-impl
         [{:keys [list-accounts list-transactions] :as k}]
         (server/accounts-impl k))))
