(ns bankng.frontend.accounts.views
  (:require [re-frame.core :as rf]
            [reitit.frontend.easy :as rfe]))

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
       [:div.skeleton {:class "h-5 max-w-44 animate-pulse"}])
     (if date
       [:span {:class "text-sm truncate"} date]
       [:div.skeleton {:class "h-5 max-w-44 animate-pulse"}])]
    (if amount
      [:div.grow.text-right {:class "pr-1"} amount]
      [:div.grow.skeleton {:class "h-8 w-28 animate-pulse"}])]])

(defn pagination [count selected-idx]
  [:div {:class "flex justify-center space-x-2 mt-8"}
   (for [i (range count)]
     ^{:key i}
     [:span.h-2.w-2.rounded-full
      {:class (if (= i selected-idx) "bg-active" "bg-gray-400")
       :on-click #(rf/dispatch [:accounts/set-active-account-idx i])}])])

(defn account-info [name currency currency-token balance]
  [:div {:class "text-center pt-20 pb-20"}
   [:div {:class "flex items-center justify-center space-x-2"}
    [:span {:class "text-gray-300 font-medium"} (str name " · " currency)]]
   [:div {:class "mt-2 text-4xl font-bold text-active"} (str currency-token " " balance)]
   [:a {:href (rfe/href :accounts-list)}
    [:button {:class "btn mt-6 btn-lg btn-primary btn-soft rounded-full"} "Accounts"]]])

(defn toolbar [current-account]
  (let [button-styles (if current-account
                        "btn btn-lg btn-soft btn-primary"
                        "btn btn-lg btn-soft btn-primary btn-disabled")]
    [:div {:class "flex flex-row justify-center gap-4 pt-10 w-full"}
     [:a {:href (rfe/href :send)}
      [:button {:class button-styles}
       [:span {:class ["icon-[tabler--credit-card-pay] size-6"]}] "Send"]]
     [:a {:href (rfe/href :account-info {:account-id (:iban current-account)})}
      [:button {:class button-styles}
       [:span {:class ["icon-[tabler--building-bank] size-6"]}] "Info"]]
     [:button {:class button-styles}
      [:span {:class ["icon-[tabler--dots] size-6"]}] "More"]]))

(defn account-card []
  (let [loading? @(rf/subscribe [:accounts/loading?])
        error @(rf/subscribe [:accounts/error])
        current-account @(rf/subscribe [:accounts/current-account])
        current-account-idx @(rf/subscribe [:accounts/current-account-idx])
        accounts-count @(rf/subscribe [:accounts/count])
        previous-route-back (-> @(rf/subscribe [:routes/previous-route]) :data :back)]
    [:div {:class ["flex flex-col"
                   (when (= previous-route-back :home) "animate-slideInLeft")]}
     (if loading?
       [:div {:class "flex items-center justify-center h-40"}
        [:span.loading.loading-spinner.loading-lg]]
       [:<>
        [account-info
         (:name current-account)
         (:currency current-account)
         (:currencyCode current-account)
         (:balance current-account)]
        [pagination accounts-count current-account-idx]])
     [toolbar current-account]

     #_[:div#transactions {:class "flex flex-col gap-2 cursor-default pt-10"}
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
        [transaction]]]))

(defn accounts-list []
  (let [accounts @(rf/subscribe [:accounts/accounts])
        on-click #(do
                    (rf/dispatch [:accounts/set-active-account-idx %])
                    (rfe/push-state :home))] 
    [:div {:class "flex flex-col gap-2 cursor-default pt-10"}
     (for [[idx acc] (map-indexed vector accounts)]
       ^{:key (:iban acc)}
       [:div.card {:class ["bg-dark-3 h-20"]
                   :on-click #(on-click idx)}
        [:div.card-body {:class ["flex flex-row items-center p-4"]}
         [:div.avatar.placeholder
          [:div.bg-dark-4.text-neutral-content.w-12.rounded-full
           [:span.text-4xl.uppercase (:currency-code acc)]]]
         [:div.grow {:class "px-4 flex flex-col justify-between w-full truncate"}
          [:span {:class "font-bold text-1xl w-full truncate"} (:name acc)]]
         [:div.text-right {:class "pr-1"}
          [:span {:class "font-bold text-2xl text-active"} (:balance acc)]]]])]))

(defn detail-entry [name value]
  [:div
   [:p.mb-1.font-bold name]
   [:div.flex.items-center.justify-center.mb-4
    [:p.whitespace-pre-wrap.grow.text-primary value]
    [:button.btn.btn-text.btn-primary
     [:span {:class ["icon-[tabler--copy]"]}]]]])

(defn info-row [icon text]
  [:div {:class "flex flex-row gap-4"}
   [:div.flex.items-center.justify-center [:span.flex-none.size-6 {:class icon}]]
   [:span.grow text]])

(defn account-details []
  (let [current-account @(rf/subscribe [:accounts/current-account])
        full-name @(rf/subscribe [:login/full-name])]
    [:div {:class "flex flex-col animate-slideInRight"}
     [:h5.card-title {:class ["mb-2.5 text-center"]}
      "For Dracones transfers only"]
     [:div.card {:class "bg-dark-3"}
      [:div.card-body
       [detail-entry "Beneficiary" full-name]
       [detail-entry "IBAN" (:iban current-account)]
       [detail-entry "Bank Name and Address"
        "Dracones Financial Services\nCity of Winds Square\nThe City of Winds\nRasahari"]]]
     [:div.card {:class "bg-dark-3 mt-2"}
      [:div.card-body {:class "gap-4"}
       [info-row "icon-[tabler--bulb]" "Use these details to receive your salary and transfers from any other account in the Dracones Financial Services network."]
       [info-row "icon-[tabler--wand]" "This account qualifies for enhanced magical protection. Your balance is secure from attempts to magically tamper with it."]
       [info-row "icon-[tabler--clock-hour-9]" "The payments on this account are instant, but they might happen in the past due to the Rift temporal fluctuations."]]]]))
