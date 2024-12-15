(ns bankng.frontend.common.views
  (:require [re-frame.core :as rf]))

(defn header
  []
  (let [pfp-url @(rf/subscribe [:login/pfp-url])
        hide-pfp @(rf/subscribe [:routes/hide-pfp-menu])
        back-route @(rf/subscribe [:routes/back-route])]
    [:div {:class "sticky top-0 z-[1] w-full"}
     [:nav.navbar.rounded-box.justify-between.bg-dark-2 {:class "shadow"}
      [:div.navbar-start
       [:div.dropdown.relative.inline-flex {:class ["[--auto-close:inside]"
                                                    "[--offset:9]"]}
        (when back-route
          [:a {:href back-route}
           [:button.btn.btn-text.btn-circle
            [:span.size-5 {:class ["icon-[tabler--arrow-left]"]}]]])]]
      [:div.navbar-center.flex.items-center {:class ["text-3xl font-bold font-fancy text-active"]}
       "Dracones Financial Services"]
      [:div.navbar-end.items-center.gap-4
       (when (and pfp-url (not hide-pfp))
         [:div.dropdown.relative.inline-flex {:class ["[--auto-close:inside]"
                                                      "[--offset:8]"
                                                      "[--placement:bottom-end]"]}
          [:button#dropdown-scrollable.dropdown-toggle.flex.items-center
           {:type "button"
            :aria-haspopup "menu"
            :aria-expanded "false"
            :aria-label "Dropdown"}
           [:div.avatar
            [:div.rounded-full {:class ["size-9.5"]}
             [:img {:src pfp-url}]]]]])]]
     [:hr {:class "w-[90%] mx-auto border-active border-t-2"}]]))

(defn footer
  []
  [:footer {:class "mt-auto text-center py-4 w-full"}
   [:p "© 2024 Dracones Financial Services"]])

(defn chrome
  [child]
  [:div {:class "relative flex flex-col w-full max-w-xs mx-auto p-4 shadow-lg h-screen bg-dark-2 safe-top safe-left safe-right safe-bottom disable-scrollbars"}
   [header]
   [:div {:class "flex-1 overflow-y-auto disable-scrollbars pt-2"}
    [child]]
   [footer]])
