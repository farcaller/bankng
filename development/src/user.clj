(ns user
  (:require [playback.preload]
            ;; [playback.nrepl-middleware :as middleware]
            ;; [shadow.cljs.devtools.api :as shadow]
            ;; [shadow.cljs.devtools.server]
            ))

(set! *warn-on-reflection* true)

(require '[dev.repl])
(dev.repl/setup!)

;; (middleware/init-refresh-on-eval!
;;  ['bankng.web-app.core/mount-root]
;;  ["bankng.web" "bankng.pb-"])
;; (shadow.cljs.devtools.server/start!)
;; (shadow/watch-set-autobuild! :frontend false)

;; (middleware/set-debug! true)
