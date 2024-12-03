(ns build
  (:require [clojure.edn :as edn]
            [clojure.tools.build.api :as b]
            [clojure.java.io :as io]
            [clojure.java.shell :refer [sh]])
  (:import [java.net URL]
           [java.nio.file Files Paths]
           [java.security MessageDigest]))

(defn get-grpc-version []
  (let [deps (-> "deps.edn" slurp edn/read-string)
        grpc-protobuf (:mvn/version (get-in deps [:deps 'io.grpc/grpc-protobuf]))]
    (if grpc-protobuf
      grpc-protobuf
      (throw (Exception. "io.grpc/grpc-protobuf is not specified in deps.edn!")))))

(defn os-platform []
  (let [os (System/getProperty "os.name")
        arch (System/getProperty "os.arch")]
    (cond
      (and (.contains os "Windows") (.contains arch "64")) "windows-x86_64.exe"
      (and (.contains os "Linux") (.contains arch "64")) "linux-x86_64.exe"
      (and (.contains os "Mac") (.contains arch "64")) "osx-x86_64"
      :else (throw (Exception. (str "Unsupported OS/architecture: " os "/" arch))))))

(defn grpc-plugin-url []
  (let [version (get-grpc-version)
        platform (os-platform)]
    (format "https://repo1.maven.org/maven2/io/grpc/protoc-gen-grpc-java/%s/protoc-gen-grpc-java-%s-%s"
            version version platform)))

(defn grpc-plugin-checksum-url []
  (format "%s.sha512" (grpc-plugin-url)))

(def grpc-plugins-download-dir "target/plugins")
(def grpc-plugin-file-path (str grpc-plugins-download-dir "/protoc-gen-grpc-java"))

(def datomic-download-dir "target/datomic")

(defn sha512 [file]
  (let [digest (java.security.MessageDigest/getInstance "SHA-512")]
    (with-open [stream (io/input-stream file)]
      (loop [buffer (byte-array 8192)]
        (let [read (.read stream buffer)]
          (when-not (= -1 read)
            (.update digest buffer 0 read)
            (recur buffer)))))
    (.digest digest)))

(defn verify-checksum [file checksum-url]
  (let [expected-checksum (slurp checksum-url)
        actual-checksum   (->> file
                               sha512
                               (map #(format "%02x" %))
                               (apply str))]
    (= expected-checksum actual-checksum)))

(defn download-file [url output-path]
  (with-open [in (io/input-stream (URL. url))
              out (io/output-stream output-path)]
    (io/copy in out))
  (println (str "Downloaded file to: " output-path)))

(defn download-plugin [_]
  (let [url         (grpc-plugin-url)
        checksum-url (grpc-plugin-checksum-url)
        output-file  (io/file grpc-plugin-file-path)]
    (.mkdirs (.getParentFile output-file))

    (download-file url grpc-plugin-file-path)

    (when-not (verify-checksum output-file checksum-url)
      (throw (Exception. "Checksum verification failed!")))

    (.setExecutable output-file true)

    (println "Downloaded and verified protoc-gen-grpc-java")))

(defn download-datomic [_]
  (let [url          "https://datomic-pro-downloads.s3.amazonaws.com/1.0.7260/datomic-pro-1.0.7260.zip"
        output-path  (str datomic-download-dir "/datomic-pro-1.0.7260.zip")
        output-file  (io/file output-path)]
    (when-not (.exists output-file)
      (.mkdirs (.getParentFile output-file))

      (download-file url output-path)
      (sh "unzip" "-d" datomic-download-dir output-path)

      (println "Downloaded and verified datomic"))))

(def proto-compiler-basis (delay (b/create-basis {:project "deps.edn" :aliases [:build]})))
(def protoc-target-directory-js "./components/pb-frontend/src/gen/js")

(defn compile-proto-files
  [search-path files & {:keys [js-out grpc-web-out java-out]}]
  (when-not (.exists (io/file grpc-plugin-file-path))
    (download-plugin nil))
  
  (when js-out (.mkdirs (io/file js-out)))
  (when grpc-web-out (.mkdirs (io/file grpc-web-out)))
  (when java-out (.mkdirs (io/file java-out)))

  (b/process {:command-args (filter some? (concat ["protoc"
                                                   "--plugin=protoc-gen-grpc-java=target/plugins/protoc-gen-grpc-java"
                                                   (when java-out (str "--java_out=" java-out))
                                                   (when java-out (str "--grpc-java_out=" java-out))
                                                   (when js-out (str "--js_out=import_style=commonjs,binary:" js-out))
                                                   (when grpc-web-out (str "--grpc-web_out=import_style=commonjs,mode=grpcwebtext:" grpc-web-out))
                                                   (str "-I" search-path)] files))}))

(defn compile-proto [_]
  (compile-proto-files "./components/pb-frontend/resources/"
                       ["auth.proto" "accounts.proto"]
                       :js-out "./components/pb-frontend/src/net/dracones/bankng/pb_frontend/gen/"
                       :grpc-web-out "./components/pb-frontend/src/net/dracones/bankng/pb_frontend/gen/"
                       :java-out "./components/pb-frontend/src")
  (compile-proto-files "./components/pb-mucklet/resources/"
                       ["mucklet.proto"]
                       :java-out "./components/pb-mucklet/src"))

(defn compile-java [_]
  (compile-proto nil)
  (b/javac {:src-dirs ["./components/pb-frontend/src"]
            :class-dir "./components/pb-frontend/target/classes"
            :basis @proto-compiler-basis
            :javac-opts ["-Xlint:-options"]})
  (b/javac {:src-dirs ["./components/pb-mucklet/src"]
            :class-dir "./components/pb-mucklet/target/classes"
            :basis @proto-compiler-basis
            :javac-opts ["-Xlint:-options"]}))

(defn run [_]
  (compile-java nil))
