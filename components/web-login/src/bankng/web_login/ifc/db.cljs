(ns bankng.web-login.ifc.db
  (:require [com.rpl.specter :refer [path]]))

(def login-db
  {; full name as entered on the login page
   :full-name nil

   ; user details as resolved through the api
   :first-name nil
   :pfp-url nil
   :session-id nil

   :jwt nil})

(def PREFIX (path :login))
