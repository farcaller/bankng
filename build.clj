(ns build
  (:require [clojure.edn :as edn]
            [clojure.tools.build.api :as b]
            [clojure.tools.deps :as t]
            [clojure.tools.deps.util.dir :refer [with-dir]]
            [clojure.java.io :as io])
  (:import [java.net URL]))

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

(defn compile-jsproto-files
  [search-path files & {:keys [js-out]}]
  (when-not (.exists (io/file grpc-plugin-file-path))
    (download-plugin nil))

  (when js-out (.mkdirs (io/file js-out)))

  (b/process {:command-args (filter some? (concat ["grpc_tools_node_protoc"
                                                   (str "--js_out=import_style=commonjs,binary:" js-out)
                                                   (str "--grpc_out=grpc_js:" js-out)
                                                   (str "-I" search-path)] files))}))

(defn compile-proto [_]
  (compile-proto-files "./components/pb-frontend/resources/"
                       ["auth.proto" "bankng.proto"]
                       :js-out "./components/pb-frontend/src/bankng/pb_frontend/gen/"
                       :grpc-web-out "./components/pb-frontend/src/bankng/pb_frontend/gen/"
                       :java-out "./components/pb-frontend/java-src")
  (compile-proto-files "./components/pb-mucklet/resources/"
                       ["mucklet.proto"]
                       :java-out "./components/pb-mucklet/java-src")
  (compile-jsproto-files "./components/pb-mucklet/resources/"
                         ["mucklet.proto"]
                         :js-out "./components/pb-mucklet/src/bankng/pb_mucklet/gen/"))

(defn compile-java [_]
  (compile-proto nil)
  (b/javac {:src-dirs ["./components/pb-frontend/java-src"]
            :class-dir "./components/pb-frontend/target/classes"
            :basis @proto-compiler-basis
            :javac-opts ["-Xlint:-options"]})
  (b/javac {:src-dirs ["./components/pb-mucklet/java-src"]
            :class-dir "./components/pb-mucklet/target/classes"
            :basis @proto-compiler-basis
            :javac-opts ["-Xlint:-options"]}))

(defn- get-project-aliases []
  (let [edn-fn (juxt :root-edn :project-edn)]
    (-> (t/find-edn-maps)
        (edn-fn)
        (t/merge-edns)
        :aliases)))

(defn- ensure-project-root
  "Given a task name and a project name, ensure the project
   exists and seems valid, and return the absolute path to it."
  [task project]
  (let [project-root (str (System/getProperty "user.dir") "/projects/" project)]
    (when-not (and project
                   (.exists (io/file project-root))
                   (.exists (io/file (str project-root "/deps.edn"))))
      (throw (ex-info (str task " task requires a valid :project option") {:project project})))
    project-root))

(defn uberjar
  "Builds an uberjar for the specified project.

   Options:
   * :project - required, the name of the project to build,
   * :uber-file - optional, the path of the JAR file to build,
     relative to the project folder; can also be specified in
     the :uberjar alias in the project's deps.edn file; will
     default to target/PROJECT.jar if not specified.

   Returns:
   * the input opts with :class-dir, :compile-opts, :main, and :uber-file
     computed.

   The project's deps.edn file must contain an :uberjar alias
   which must contain at least :main, specifying the main ns
   (to compile and to invoke)."
  [{:keys [project uber-file] :as opts}]
  (let [_            (compile-java nil)
        project-root (ensure-project-root "uberjar" project)
        aliases      (with-dir (io/file project-root) (get-project-aliases))
        main         (-> aliases :uberjar :main)]
    (when-not main
      (throw (ex-info (str "the " project " project's deps.edn file does not specify the :main namespace in its :uberjar alias")
                      {:aliases aliases})))
    (b/with-project-root project-root
      (let [class-dir "target/classes"
            uber-file (or uber-file
                          (-> aliases :uberjar :uber-file)
                          (str "target/" project ".jar"))
            opts      (merge opts
                             {:basis        (b/create-basis)
                              :class-dir    class-dir
                              :compile-opts {:direct-linking true}
                              :main         main
                              :ns-compile   [main]
                              :uber-file    uber-file})]
        (b/delete {:path class-dir})
        ;; no src or resources to copy
        (println "\nCompiling" (str main "..."))
        (b/compile-clj opts)
        (println "Building uberjar" (str uber-file "..."))
        (b/uber opts)
        (b/delete {:path class-dir})
        (println "Uberjar is built.")
        opts))))
