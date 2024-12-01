(ns net.dracones.bankng.log.interface
  (:require [taoensso.telemere :as log]))

(defmacro debug [msg] (log/log! :debug msg))

(defmacro info
  ([msg] (log/log! :info msg))
  ([msg dta] (log/log! {:level :info :data dta} msg)))

(defmacro error
  ([msg err] (log/log! {:error err} msg))
  ([msg] (log/log! msg)))
