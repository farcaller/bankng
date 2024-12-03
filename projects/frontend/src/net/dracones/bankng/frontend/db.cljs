(ns net.dracones.bankng.frontend.db)

(def login-db
  {; true when loading the 2fa
   :loading? false
   :error nil
   
   ; full name as entered on the login page
   :full-name nil

   ; user details as resolved through the api
   :first-name nil
   :pfp-url nil
   :session-id nil

   :jwt nil
   })

(def default-db
  {; the current reitit route
   :current-route nil

   :login login-db})
