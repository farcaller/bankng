(ns net.dracones.bankng.frontend.login.views
  (:require [reagent.core :as r]
            [re-frame.core :as rf]))

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

(defn profile-picture
  ([pic-url]
   (if pic-url
     [:div
      {:class "avatar mb-2"}
      [:div
       {:class "size-16 rounded-full"}
       [:img {:src pic-url}]]]
     [:div
      {:class "avatar placeholder mb-2"}
      [:div
       {:class "bg-neutral text-neutral-content w-16 rounded-full"}
       [:span {:class "icon-[tabler--user] size-10"}]]]))
  ([] (profile-picture nil)))

(defn login-name
  []
  (let [input-name (r/atom "")]
    (fn []
      [:div {:class "text-center mt-4"}
       [profile-picture]
       [:h1
        {:class "text-2xl font-bold"}
        "Welcome!"]

       [:div
        {:class
         "mx-auto text-center py-10 w-[16.25rem]"}
        [:header
         [:h1 {:class "text-xl font-bold mb-1"} "Sign In"]]
        [:label
         {:class "form-control mb-2 mt-2"}
         [:div {:class "label"} [:span {:class "label-text"} "Full Name"]]
         [:input {:type "text"
                  :placeholder "Jane Doe"
                  :class "input"
                  :value @input-name
                  :id :username
                  :on-change #(reset! input-name (-> % .-target .-value))}]
         [:div
          {:class "label"}
          [:span {:class "label-text-alt"} "Please type in your full name."]]]

        [:button {:class "btn btn-primary mt-2 btn-block text-dark-1"
                  :on-click #(rf/dispatch [:login/send-otp-code @input-name])} "Send Verification Code"]]])))

(defn login-otp
  []
  (let [input-values (r/atom (vec (repeat 4 "")))
        input-refs (r/atom (vec (repeat 4 nil)))
        first-name @(rf/subscribe [:login/first-name])
        pfp-url @(rf/subscribe [:login/pfp-url])]
    (fn []
      [:div
       {:class "text-center mt-4"}
       [profile-picture pfp-url]
       [:h1
        {:class "text-2xl font-bold"}
        (str "Welcome, " first-name "!")]

       ; https://cruip.com/otp-form-example-made-with-tailwind-css-and-javascript/
       [:div
        {:class
         "max-w-md mx-auto text-center px-4 sm:px-8 py-10"}
        [:header
         {:class "mb-8"}
         [:h1 {:class "text-xl font-bold mb-1"} "Account Verification"]
         [:p
          {:class "text-[15px]"}
          "Enter the 4-digit verification code that was sent to you."]]
        [:form
         {:id "otp-form"}
         [:div
          {:class "flex items-center justify-center gap-3"}

          (doall
           (for [i (range 4)]
             (letfn [(on-key-down
                       [ev]
                       (let [key (.-key ev)]
                         (when (and (not (re-matches #"^\d$" key))
                                    (not= key "Backspace")
                                    (not= key "Delete")
                                    (not= key "Tab")
                                    (not (.-ctrlKey ev))
                                    (not (.-metaKey ev)))
                           (.preventDefault ev))
                         (when (or (= key "Delete") (= key "Backspace"))
                           (swap! input-values assoc i "")
                           (when (> i 0) (.focus (get @input-refs (dec i)))))))
                     (on-input
                       [ev]
                       (let [value (-> ev .-target .-value)]
                         (when-not (empty? value)
                           (swap! input-values assoc i value)
                           (when (< i 3)
                             (.focus (get @input-refs (inc i)))))))
                     (on-paste
                       [ev]
                       (.preventDefault ev)
                       (let [text (-> ev .-clipboardData (.getData "text"))]
                         (when (re-matches #"^[0-9]{4}$" text)
                           (doall
                            (for [i (range 4)]
                              (swap! input-values assoc i (get text i)))))))]
               ^{:key i}
               [:input
                {:type "text",
                 :class ["w-14" "h-14" "text-center" "text-2xl" "font-extrabold"
                         "text-active" "bg-dark-3" "border" "border-transparent"
                         "hover:border-active" "appearance-none" "rounded" "p-4"
                         "outline-none" "focus:bg-dark-4" "focus:ring-2"
                         "focus:ring-dark-4"]
                 :pattern "\\d*"
                 :max-length "1"
                 :value (get @input-values i)
                 :ref #(swap! input-refs assoc i %)
                 :on-key-down on-key-down
                 :on-input on-input
                 :on-paste on-paste
                 :on-focus (fn [ev] (-> ev .-target .select))}])))]
         [:div
          {:class "mx-auto mt-4 w-[16.25rem]"}
          [:button.text-dark-1.btn.btn-primary.mt-2.btn-block
           {:type "submit"}
           "Verify Account"]]]]])))

(defn login-page
  []
  [:div {:class "flex justify-center items-center min-h-screen bg-dark-1"}
   [:div {:class "flex flex-col w-full max-w-xs mx-auto p-4 shadow-lg min-h-[90vh] h-auto bg-dark-2"}
    [header]
    [login-name]
    [footer]]])

(defn login-otp-page
  []
  [:div {:class "flex justify-center items-center min-h-screen bg-dark-1"}
   [:div {:class "flex flex-col w-full max-w-xs mx-auto p-4 shadow-lg min-h-[90vh] h-auto bg-dark-2"}
    [header]
    [login-otp]
    [footer]]])
