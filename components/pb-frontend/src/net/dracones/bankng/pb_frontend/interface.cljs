(ns net.dracones.bankng.pb-frontend.interface
  (:require [net.dracones.bankng.pb-frontend.core :as core]))

(defn first-factor [full-name]
  (core/first-factor full-name))
