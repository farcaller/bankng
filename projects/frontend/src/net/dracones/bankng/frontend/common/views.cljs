(ns net.dracones.bankng.frontend.common.views)

(defn header
  []
  [:header {:class "text-center py-4"}
   [:h1 {:class "text-3xl font-bold font-fancy text-active"}
    "Dracones Financial Services"]
   [:hr {:class "w-[90%] mx-auto border-active border-t-2"}]])

(defn footer
  []
  [:footer {:class "mt-auto text-center py-4 w-full"}
   [:p "© 2024 Dracones Financial Services"]])

(defn chrome
  [child]
  [:div {:class "flex justify-center items-center min-h-screen bg-dark-1"}
   [:div {:class "flex flex-col w-full max-w-xs mx-auto p-4 shadow-lg min-h-[90vh] h-auto bg-dark-2"}
    [header]
    [child]
    [footer]]])
