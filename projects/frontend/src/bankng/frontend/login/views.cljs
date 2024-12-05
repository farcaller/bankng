(ns bankng.frontend.login.views
  (:require
   [bankng.frontend.common.views :refer [chrome]]
   [re-frame.core :as rf]
   [reagent.core :as r]))

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

(defn welcome-pfp
  []
  (let [first-name @(rf/subscribe [:login/first-name])
        pfp-url @(rf/subscribe [:login/pfp-url])]
    (if (and first-name pfp-url)
      [:<> [profile-picture pfp-url]
           [:h1
            {:class "text-2xl font-bold"}
            (str "Welcome, " first-name "!")]]
      [:<> [profile-picture]
           [:h1
            {:class "text-2xl font-bold"}
            "Welcome!"]])))

(defn login-name
  []
  (let [!input-name (atom nil)
        send-code #(rf/dispatch [:login/send-otp-code (-> @!input-name .-value)])]
    (fn []
      (let [loading? @(rf/subscribe [:login/loading?])
            error @(rf/subscribe [:login/error])]
        [:div {:class "text-center mt-4"}
         [welcome-pfp]

         [:div
          {:class
           "mx-auto text-center py-10 w-[16.25rem]"}
          [:header
           [:h1 {:class "text-xl font-bold mb-1"} "Sign In"]]
          [:label
           {:class "form-control mb-2 mt-2"}
           [:div {:class "label"} [:span {:class "label-text"} "Full Name"]]
           [:input {:auto-focus true
                    :type "text"
                    :placeholder "Jane Doe"
                    :class ["input" (when error "is-invalid")]
                    :ref #(reset! !input-name %)
                    :id :username
                    :disabled loading?
                    :on-key-press #(when (= "Enter" (.-key %)) (send-code))}]
           [:div
            {:class "label"}
            (if-not error
              [:span {:class "label-text-alt"} "Please type in your full name."]
              [:<>
               [:span {:class "label-text-alt"} ""]
               [:span {:class "label-text-alt"} (.-message error)]])]]

          [:button {:class "btn btn-primary mt-2 btn-block text-dark-1"
                    :on-click send-code
                    :disabled loading?}
           (when loading? [:span.loading.loading-spinner.loading-sm])
           "Send Verification Code"]]]))))

(defn login-otp
  []
  (let [input-values (r/atom (vec (repeat 4 "")))
        input-refs (r/atom (vec (repeat 4 nil)))
        on-submit #(rf/dispatch [:login/verify-otp-code (apply str %)])]
    (fn []
      (let [loading? @(rf/subscribe [:login/loading?])
            error @(rf/subscribe [:login/error])]
        [:div
         {:class "text-center mt-4"}
         [welcome-pfp]

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
                             (when (> i 0) (.focus (get @input-refs (dec i)))))
                           (when (and (= i 3) (= key "Enter"))
                             (on-submit @input-values))))
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
                                (swap! input-values assoc i (get text i))))
                             (on-submit @input-values))))]
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

           (when error
             [:div {:class "text-error pt-6"} (.-message error)])

           [:div
            {:class "mx-auto mt-4 w-[16.25rem]"}
            [:button.text-dark-1.btn.btn-primary.mt-2.btn-block
             {:disabled loading?
              :type "submit"
              :on-click #(do (.preventDefault %)
                             (on-submit @input-values))}
             (when loading? [:span.loading.loading-spinner.loading-sm]) "Verify Account"]]]]]))))

(defn login-page
  []
  [chrome login-name])

(defn login-otp-page
  []
  [chrome login-otp])
