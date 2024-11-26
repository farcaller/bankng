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
  processes = {
    mucklet-proxy.exec = "node target/mucklet-proxy-main.js";
    mucklet-proxy.process-compose.environment = [
      "SERVER_LISTEN_ADDRESS=127.0.0.1:8081"
      "PROTO_PATH=./components/pb-mucklet/resources/mucklet.proto"
    ];
    envoy.exec = "envoy -c envoy.yaml";
    postcss-watch.exec = "postcss ./projects/frontend/resources/css/tailwind.css -o ./public/css/main.css --verbose -w --poll 500";
  };
}
