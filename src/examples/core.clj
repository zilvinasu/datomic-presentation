(ns examples.core
  (:gen-class)
  (:require [datomic.api :as d]))

(defn empty-db-conn []
  "Creates empty in-memory db and returns the connection"
  (let [db-uri "datomic:mem://vilnius-clojure"]
    (d/delete-database db-uri)
    (d/create-database db-uri)
    (d/connect db-uri)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
