(ns net.dracones.bankng.frontend.accounts.views
  (:require [net.dracones.bankng.frontend.common.views :refer [chrome]]))

(defn transaction [pfp-url name date amount]
  [:div.card {:class ["bg-dark-3 h-20"]}
   [:div.card-body {:class ["flex flex-row items-center p-2 cursor-pointer"]}
    [:div {:class "avatar"}
     (if pfp-url
       [:div {:class "size-14 rounded-full"} [:img {:src pfp-url}]]
       [:div.skeleton {:class "h-14 w-14 shrink-0 rounded-full animate-pulse"}])]
    [:div {:class "px-4 flex flex-col justify-between h-full w-full truncate"}
     (if name
       [:span {:class "font-bold w-full truncate"} name]
       [:div.skeleton {:class "h-5 w-full animate-pulse"}])
     (if date
       [:span {:class "text-sm truncate"} date]
       [:div.skeleton {:class "h-5 max-w-44 animate-pulse"}])]
    (if amount
      [:div.grow.text-right {:class "pr-1"} amount]
      [:div.grow.skeleton {:class "h-8 w-28 animate-pulse"}])]])

(defn account-card []
  [:div {:class "flex flex-col"}
   [:div {:class "text-center pt-20 pb-20"}
    [:div {:class "flex items-center justify-center space-x-2"}
     [:span {:class "text-gray-300 font-medium"} "Personal · SCAM"]]
    [:div {:class "mt-2 text-4xl font-bold text-active"} "₷ 1234.56"]
    [:button {:class "btn mt-6 btn-lg btn-primary btn-soft rounded-full"} "Accounts"]]

   [:div {:class "flex justify-center space-x-2 mt-8"}
    [:span {:class "h-2 w-2 bg-gray-400 rounded-full"}]
    [:span {:class "h-2 w-2 bg-active rounded-full"}]
    [:span {:class "h-2 w-2 bg-gray-400 rounded-full"}]
    [:span {:class "h-2 w-2 bg-gray-400 rounded-full"}]]

   [:div {:class "flex flex-row justify-center gap-4 pt-10 pb-10 w-full"}
    [:button {:class "btn btn-lg btn-soft btn-primary"}
     [:span {:class ["icon-[tabler--credit-card-pay] size-6"]}] "Send"]
    [:button {:class "btn btn-lg btn-soft btn-primary"}
     [:span {:class ["icon-[tabler--building-bank] size-6"]}] "Info"]
    [:button {:class "btn btn-lg btn-soft btn-primary"}
     [:span {:class ["icon-[tabler--dots] size-6"]}] "More"]]

   [:div#transactions {:class "flex flex-col gap-2 cursor-default"}
    [transaction
     "https://api.dicebear.com/7.x/pixel-art/png?seed=wolf"
     "Anonymous Wolf"
     "22 December 2022, 09:21"
     "-1234.32"]
    [transaction
     "https://api.dicebear.com/7.x/pixel-art/png?seed=boar"
     "Anonymous Boar"
     "22 December 2022, 09:20"
     "-9999.69"]
    [transaction]]])

(defn account-page
  []
  [chrome account-card])
