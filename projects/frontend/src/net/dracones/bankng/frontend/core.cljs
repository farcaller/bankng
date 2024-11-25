(ns net.dracones.bankng.frontend.core
  (:require ["react" :as react]
            [net.dracones.bankng.frontend.routes :as routes]
            [re-frame.core :as rf]
            [reagent.dom.client :as rdomc]
            #_[frontend.events :as ev]))

(defn dev-setup []
  (when goog.DEBUG
    (enable-console-print!)
    (println "dev mode")))

(defonce react-root
  (delay (rdomc/create-root (js/document.getElementById "app"))))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (rdomc/render @react-root [:> react/StrictMode {} [routes/router-component]]))

(defn init
  []
  ; #_(rf/dispatch-sync [::ev/initialize-db])
  (routes/start-router!)
  (dev-setup)
  (mount-root))
