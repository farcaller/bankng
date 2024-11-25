(ns net.dracones.bankng.pb-mucklet.interface
  (:import [net.dracones.bankng.mucklet MuckletGrpc]))

(defn build-stub
  [channel]
  (MuckletGrpc/newFutureStub channel))
