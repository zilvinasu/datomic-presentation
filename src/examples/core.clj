(ns examples.core
  (:gen-class)
  (:require [datomic.api :as d]))


(defn has-attribute? [conn attribute]
  "Takes `conn` and `attribute`
  then checks whether it is in db"
  (boolean (d/entity (d/db conn) attribute)))

(defn empty-db-conn []
  "Creates empty in-memory db and returns the connection"
  (let [db-uri "datomic:mem://vilnius-clojure"]
    (d/delete-database db-uri)
    (d/create-database db-uri)
    (d/connect db-uri)))

(defn q-by-email [db email]
  "Takes `db`, `email` and pulls the entity
  for which the email matches"
  (d/query
    {:query '[:find (pull ?e [*])
              :in $ ?email
              :where [?e :contact/email ?email]]
     :args  [db email]}))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
