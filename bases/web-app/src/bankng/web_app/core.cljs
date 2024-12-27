(ns bankng.web-app.core
  (:require ["react" :as react]
            [re-frame.core :as rf]
            [reagent.dom.client :as rdomc]
            [bankng.web-db.ifc :as db]
            [bankng.web-login.ifc.events]
            [bankng.web-login.ifc.subs]
            [bankng.web-accounts.ifc.events]
            [bankng.web-accounts.ifc.subs]
            [bankng.web-routes.ifc.router :as routes]
            [bankng.web-routes.ifc.events]
            [bankng.web-routes.ifc.subs]
            [bankng.web-transfers.ifc.events]
            [bankng.web-transfers.ifc.subs]))

(defn dev-setup []
  (when goog.DEBUG
    (enable-console-print!)
    (js/eval "try { day8.re_frame_10x.show_panel_BANG_(false) } catch (e) { console.log(`re-frame-10x:`, e) }")
    (println "dev mode")))

(defonce react-root
  (delay (rdomc/create-root (js/document.getElementById "app"))))

(defn ^:dev/after-load mount-root []
  (when goog.DEBUG (.log js/console "remount-root"))
  (rf/clear-subscription-cache!)
  (rdomc/render @react-root [:> react/StrictMode {} [routes/router-component]]))

(defn init
  []
  (rf/dispatch-sync [::db/initialize-db])
  (routes/start-router!)
  (dev-setup)
  (mount-root))
