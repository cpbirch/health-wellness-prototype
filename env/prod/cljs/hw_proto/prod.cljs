(ns hw-proto.prod
  (:require [hw-proto.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
