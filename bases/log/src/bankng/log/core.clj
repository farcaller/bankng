(ns bankng.log.core
  (:require [taoensso.telemere :as t]
            [taoensso.telemere.open-telemetry :as ot]
            [mount.core :refer [defstate]]))

(defn setup-logging! []
  (t/remove-handler! :default/console)
  (t/add-handler! :default/console
                  (t/handler:console)
                  {:kind-filter {:disallow [:trace]}})
  (when (= "true" (System/getProperty "taoensso.telemere.otel-tracing"))
    (t/add-handler! :otel (ot/handler:open-telemetry))))

(defstate logging :start (setup-logging!))
