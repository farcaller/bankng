(ns bankng.web-transfers.ifc.views 
  (:require [re-frame.core :as rf]))

(defn create-transfer []
  (let [accounts @(rf/subscribe [:accounts/accounts])
        source-account @(rf/subscribe [:transfers/source-account])
        recipient-type @(rf/subscribe [:transfers/recipient-type])]
    [:div {:class "flex flex-col items-stretch gap-6 max-w-full p-4"}
     [:div.relative.w-full
      [:select#select-from-account.select.select-floating
       {:default-value (:iban source-account)
        :on-change #(rf/dispatch [:transfers/set-source-account (-> % .-target .-value)])}
       (for [acc accounts]
         ^{:key (:iban acc)}
         [:option {:value (:iban acc)} (:name acc)])]
      [:label.select-floating-label {:for "select-from-account"}
       "From Account"]
      [:span.label
       [:span.label-text-alt]
       [:span.label-text-alt (str "Available Balance: " (:currency-code source-account) " " (:balance source-account))]]]
     [:div.join.drop-shadow.w-full.flex
      [:input.join-item.btn.flex-1
       {:class "bg-b1 border-bc hover:bg-b1 hover:border-bc-hover checked:!text-slate-800 text-bc-text"
        :type "radio" :name "radio-recipient-type" :aria-label "To Named Recipient"
        :checked (= recipient-type :name)
        :on-change #(rf/dispatch [:transfers/set-recipient-type :name])}]
      [:input.join-item.btn.flex-1
       {:class "bg-b1 border-bc hover:bg-b1 hover:border-bc-hover checked:!text-slate-800 text-bc-text"
        :type "radio" :name "radio-recipient-type" :aria-label "To Account IBAN"
        :checked (= recipient-type :iban)
        :on-change #(rf/dispatch [:transfers/set-recipient-type :iban])}]]
     (if (= recipient-type :name)
       [:div.input-group.w-full
        [:span.input-group-text
         [:span.size-5 {:class ["icon-[tabler--user]" "text-base-content/80"]}]]
        [:div.grow.relative
         [:input#leadingIconFloating.input.input-floating.peer
          {:type "text" :placeholder "Jane Doe"}]
         [:label.input-floating-label {:for "leadingIconFloating"}
          "Recipient Full Name"]]]
       [:div.input-group.w-full
        [:span.input-group-text
         [:span.size-5 {:class ["icon-[tabler--wallet]" "text-base-content/80"]}]]
        [:div.grow.relative
         [:input#leadingIconFloating.input.input-floating.peer
          {:type "text" :placeholder "WY66RASD42069123"}]
         [:label.input-floating-label {:for "leadingIconFloating"}
          "Recipient IBAN"]]])
     [:div.input-group.w-full
      [:span.input-group-text
       [:span.size-5 {:class ["text-base-content/80"]} (:currency-code source-account)]]
      [:div.grow.relative
       [:input#leadingIconFloating.input.input-floating.peer
        {:type "text" :placeholder "0.00"}]
       [:label.input-floating-label {:for "leadingIconFloating"}
        "Amount"]]]
     [:button.btn.btn-primary.!text-slate-800 "Complete Transfer"]]))
