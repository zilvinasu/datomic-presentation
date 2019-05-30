(ns examples.utils
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(defn read-string [resource]
  (edn/read-string {:readers *data-readers*} resource))

(defn resource->edn [resource]
  (-> (io/resource resource) slurp read-string))
