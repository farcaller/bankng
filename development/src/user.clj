(ns user
  (:require [playback.preload]))

(set! *warn-on-reflection* true)

(require '[dev.repl])
(dev.repl/setup!)
