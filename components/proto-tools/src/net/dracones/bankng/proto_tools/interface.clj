(ns net.dracones.bankng.proto-tools.interface
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

(def ABORTED (Status/ABORTED))
(def ALREADY_EXISTS (Status/ALREADY_EXISTS))
(def CANCELLED (Status/CANCELLED))
(def DATA_LOSS (Status/DATA_LOSS))
(def DEADLINE_EXCEEDED (Status/DEADLINE_EXCEEDED))
(def FAILED_PRECONDITION (Status/FAILED_PRECONDITION))
(def INTERNAL (Status/INTERNAL))
(def INVALID_ARGUMENT (Status/INVALID_ARGUMENT))
(def NOT_FOUND (Status/NOT_FOUND))
(def OK (Status/OK))
(def OUT_OF_RANGE (Status/OUT_OF_RANGE))
(def PERMISSION_DENIED (Status/PERMISSION_DENIED))
(def RESOURCE_EXHAUSTED (Status/RESOURCE_EXHAUSTED))
(def UNAUTHENTICATED (Status/UNAUTHENTICATED))
(def UNAVAILABLE (Status/UNAVAILABLE))
(def UNIMPLEMENTED (Status/UNIMPLEMENTED))
(def UNKNOWN (Status/UNKNOWN))
