(ns bankng.web-fx-common.ifc
  (:require [com.rpl.specter :refer [path multi-path terminal-val setval select-one]]))

(def EFFECT-DB (path [:effects :db]))
