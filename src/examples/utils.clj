(ns examples.utils
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(defn read-edn [resource]
  (-> (io/resource resource) slurp edn/read-string))
