(ns bankng.frontend.core
  (:require ["react" :as react]
            [bankng.frontend.routes :as routes]
            [re-frame.core :as rf]
            [reagent.dom.client :as rdomc]
            [bankng.frontend.events :as ev]
            [bankng.frontend.login.events]
            [bankng.frontend.login.subs]))

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
  (rf/dispatch-sync [::ev/initialize-db])
  (routes/start-router!)
  (dev-setup)
  (mount-root))