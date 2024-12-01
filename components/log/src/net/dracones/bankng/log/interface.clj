(ns net.dracones.bankng.log.interface
  (:require [taoensso.telemere :as log]))

(defmacro debug [msg] (log/log! :debug msg))

(defmacro info [msg] (log/log! :info msg))
