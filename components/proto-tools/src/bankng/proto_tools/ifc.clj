(ns bankng.proto-tools.ifc
  (:require [camel-snake-kebab.core :as csk])
  (:import [io.grpc Status Status$Code]))

(defn proto->map
  [proto-obj]
  (let [fields (.getAllFields proto-obj)]
    (reduce (fn [acc [field-descriptor value]]
              (assoc acc (csk/->kebab-case-keyword (.getName field-descriptor)) value))
            {}
            fields)))

(defmacro map->proto [class-name field-map]
  (let [builder-sym (gensym "builder")]
    `(let [~builder-sym (~(symbol (str class-name "/newBuilder")))]
       ~@(for [[field-key field-value] field-map]
           (let [setter-name (symbol (str ".set" (csk/->PascalCaseString field-key)))]
             `(~setter-name ~builder-sym ~field-value)))
       (.build ~builder-sym))))

(defn ->status [th]
  (Status/fromThrowable th))

(defn ->statuserr [th]
  (-> (->status th) (.asRuntimeException)))

(defn ex->status [s th msg]
  (-> (.withDescription s msg)
      (.withCause th)))

(defn code? [status]
  (-> (.getCode status)
      .name
      keyword))

(defn ->err
  "Creates a Throwable from a status with an optional cause."
  ([status msg]
   (-> status
       (.withDescription msg)
       (.asRuntimeException)))
  ([status cause msg]
   (-> status
       (.withDescription msg)
       (.withCause cause)
       (.asRuntimeException))))

(defn throw-status! [status msg]
  (throw (-> status
             (.withDescription msg)
             (.asRuntimeException))))
