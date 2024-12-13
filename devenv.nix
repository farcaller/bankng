{ pkgs, ... }: {
  languages.java.enable = true;
  languages.clojure.enable = true;
  languages.javascript.enable = true;
  languages.javascript.package = pkgs.nodejs;
  dotenv.enable = true;
  packages = with pkgs; [
    babashka
    protobuf
    grpcurl
    protoc-gen-js
    protoc-gen-grpc-web
    envoy
    polylith
  ];

  services.redis.enable = true;
  services.opentelemetry-collector.enable = true;
  services.opentelemetry-collector.settings = {
    extensions.health_check.endpoint = "localhost:13133";

    receivers.otlp.protocols = {
      grpc.endpoint = "127.0.0.1:4317";
      http.endpoint = "127.0.0.1:4318";
    };

    service = {
      extensions = [ "health_check" ];
      pipelines.traces = {
        receivers = [ "otlp" ];
        processors = [];
        exporters = [ "otlp" ];
      };
    };

    exporters.otlp = {
      endpoint = "http://localhost:14317";
      tls.insecure = true;
    };

    processors = {};
  };
  processes = {
    mucklet-proxy.exec = "node target/mucklet-proxy-main.js";
    mucklet-proxy.process-compose.environment = [
      "SERVER_LISTEN_ADDRESS=127.0.0.1:8081"
      "PROTO_PATH=./components/pb-mucklet/resources/mucklet.proto"
      "OTEL_EXPORTER_OTLP_TRACES_ENDPOINT=http://127.0.0.1:4317"
      "OTEL_AUTOINSTRUMENT=true"
      "OTEL_SERVICE_NAME=mucklet-proxy"
    ];
    envoy.exec = "envoy -c envoy.yaml";
    envoy.process-compose.environment = [
      "OTEL_SERVICE_NAME=envoy-ingress"
    ];
    postcss-watch.exec = "postcss ./projects/frontend/resources/css/tailwind.css -o ./public/css/main.css --verbose -w --poll 500";
    jaeger.exec = "docker run --rm -i -e COLLECTOR_ZIPKIN_HOST_PORT=:9411 -p 16686:16686 -p 14317:4317 jaegertracing/all-in-one:latest";
  };
}
