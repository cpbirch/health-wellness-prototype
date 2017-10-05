(ns ^:figwheel-no-load hw-proto.dev
  (:require
    [hw-proto.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)
