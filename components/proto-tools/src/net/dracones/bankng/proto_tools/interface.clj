(ns net.dracones.bankng.proto-tools.interface
  (:require [camel-snake-kebab.core :as csk])
  (:import [io.grpc Status]))

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

(defn ->statusex [th]
  (-> (Status/fromThrowable th) (.asRuntimeException)))
