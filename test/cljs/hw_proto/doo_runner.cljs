(ns hw-proto.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [hw-proto.core-test]))

(doo-tests 'hw-proto.core-test)
