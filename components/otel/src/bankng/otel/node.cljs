(ns bankng.otel.node
  (:require ["@opentelemetry/sdk-trace-base" :refer [SimpleSpanProcessor]]
            ["@opentelemetry/exporter-trace-otlp-grpc" :refer [OTLPTraceExporter]]
            ["@opentelemetry/auto-instrumentations-node" :refer [getNodeAutoInstrumentations]]
            ["@opentelemetry/instrumentation-grpc" :refer [GrpcInstrumentation]]
            ["@opentelemetry/sdk-trace-node" :refer [NodeTracerProvider]]
            ["@opentelemetry/instrumentation" :refer [registerInstrumentations]]
            ["@opentelemetry/semantic-conventions" :refer [SEMRESATTRS_SERVICE_NAME]]
            ["@opentelemetry/resources" :refer [Resource]]))

(defonce span-processors
  (when-let [url (-> js/process .-env .-OTEL_EXPORTER_OTLP_TRACES_ENDPOINT)]
    (.log js/console "Will be sending traces to" url)
    [(SimpleSpanProcessor. (OTLPTraceExporter. #js {:url url}))]))

(defonce instrumentations
  (if (= (-> js/process .-env .-OTEL_AUTOINSTRUMENT) "true")
    (do
      (.log js/console "Will be autoinstrumenting")
      #js [(getNodeAutoInstrumentations) (GrpcInstrumentation.)])
    #js []))

(defonce provider
  (let [p (NodeTracerProvider. #js {:spanProcessors (clj->js span-processors)
                                    :resource (Resource. (clj->js {SEMRESATTRS_SERVICE_NAME "mucklet-proxy"}))})]
    (.register p)
    (registerInstrumentations #js {:instrumentations instrumentations})))
