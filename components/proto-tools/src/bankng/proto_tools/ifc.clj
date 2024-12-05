(ns bankng.proto-tools.ifc
  (:require [camel-snake-kebab.core :as csk])
  (:import [io.grpc Status Status$Code]
           [com.google.protobuf GeneratedMessageV3 Descriptors$FieldDescriptor]))

(defn proto->map
  [^GeneratedMessageV3 proto-obj]
  (let [fields (.getAllFields proto-obj)]
    (reduce (fn [acc [^Descriptors$FieldDescriptor field-descriptor value]]
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

(defn ->status ^Status [th]
  (Status/fromThrowable th))

(defn ->statuserr [th]
  (-> (->status th) (.asRuntimeException)))

(defn ex->status ^Status [^Status s th msg]
  (-> (.withDescription s msg)
      (.withCause th)))

(defn code? [^Status status]
  (-> (.getCode status)
      .name
      keyword))

(defn ->err
  "Creates a Throwable from a status with an optional cause."
  ([^Status status msg]
   (-> status
       (.withDescription msg)
       (.asRuntimeException)))
  ([^Status status cause msg]
   (-> status
       (.withDescription msg)
       (.withCause cause)
       (.asRuntimeException))))

(defn throw-status! [^Status status msg]
  (throw (-> status
             (.withDescription msg)
             (.asRuntimeException))))
