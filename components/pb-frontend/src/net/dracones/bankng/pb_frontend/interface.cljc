(ns net.dracones.bankng.pb-frontend.interface
  #?(:cljs (:require [net.dracones.bankng.pb-frontend.client :as client])
     :clj (:require [net.dracones.bankng.pb-frontend.server :as server])))

#?(:cljs
   (defn first-factor [full-name]
     (client/first-factor full-name)))

#?(:clj
   (defn auth-impl
     [on-first-factor on-second-factor]
     (server/auth-impl on-first-factor on-second-factor)))
