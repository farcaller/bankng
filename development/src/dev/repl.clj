(ns dev.repl
  (:require [bankng.grpc-server.core]
            [bankng.log.core]
            [playback.preload]
            [mount.core :as mount]
            [mount-up.core :as mu]
            [taoensso.telemere :as log]
            [clojure.tools.namespace.repl :as tn]
            [bankng.xtdb-inproc.mocks :as mocks]))

(defn stop []
  (mount/stop))

(defn start []
  (tap> {:mount (mount/start)}))

(defn refresh []
  (stop)
  (tn/refresh))

(defn refresh-all []
  (stop)
  (tn/refresh-all))

(defn go
  "starts all states defined by defstate"
  []
  (start)
  :ready)

(defn reset
  "stops all states defined by defstate, reloads modified source files, and restarts the states"
  []
  (stop)
  (tn/refresh :after 'dev.repl/go))

(mount/in-clj-mode)

(defn setup! []
  (set! *warn-on-reflection* true)
  (log/set-min-level! :info)
  (mu/on-upndown :info mu/log :before)
  (start)
  (mocks/seed))
