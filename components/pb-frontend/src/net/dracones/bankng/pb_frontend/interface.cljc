(ns net.dracones.bankng.pb-frontend.interface
  #?(:cljs (:require [net.dracones.bankng.pb-frontend.client :as client])
     :clj (:require [net.dracones.bankng.pb-frontend.server :as server])))

#?(:cljs
   (do
     (defn first-factor [full-name]
       (client/first-factor full-name))
     
     (defn second-factor [session-id code]
       (client/second-factor session-id code))))

#?(:clj
   (do (defn auth-impl
         [on-first-factor on-second-factor]
         (server/auth-impl on-first-factor on-second-factor))
       
       (defn accounts-impl
         [{:keys [list-accounts list-transactions] :as k}]
         (server/accounts-impl k))))
