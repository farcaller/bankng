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
    postcss-watch.exec = "postcss ./projects/frontend/resources/css/tailwind.css -o ./public/css/main.css --verbose -w";
  };
}
