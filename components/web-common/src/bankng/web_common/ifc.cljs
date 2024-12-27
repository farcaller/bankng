(ns bankng.web-common.ifc
  (:require [re-frame.core :as rf]))

(defn header
  []
  (let [pfp-url @(rf/subscribe [:login/pfp-url])
        hide-pfp @(rf/subscribe [:routes/hide-pfp-menu])
        back-route @(rf/subscribe [:routes/back-route])]
    [:div {:class "sticky top-0 z-[1] w-full overscroll-none"}
     [:nav.navbar.rounded-box.justify-between.bg-dark-2 {:class "shadow max-sm:pt-0"}
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
  [:div {:class "relative flex flex-col w-full max-w-xs mx-auto p-4 shadow-lg h-screen bg-dark-2 safe-top safe-left safe-right safe-bottom overflow-hidden overscroll-none"}
   [:div {:class "max-sm:hidden w-full h-2 bg-dark-1"}]
   [header]
   [:div {:class "flex-1 overflow-y-auto p-2 overscroll-contain disable-scrollbars"}
    [child]]
   [footer]
   [:div {:class "max-sm:hidden w-full h-2 bg-dark-1"}]])
