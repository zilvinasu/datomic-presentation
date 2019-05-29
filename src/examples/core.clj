(ns examples.core
  (:gen-class)
  (:require [datomic.api :as d]
            [clojure.java.io :as io]
            [clojure.edn :as edn]))


(def ^:private db-uri "datomic:mem://vilnius-clojure")


(defn has-attribute? [conn attribute]
  "Takes `conn` and `attribute`
  then checks whether it is in db"
  (boolean (d/entity (d/db conn) attribute)))

(defn empty-db-conn []
  "Creates empty in-memory db and returns the connection"
  (d/delete-database db-uri)
  (d/create-database db-uri)
  (d/connect db-uri))

(defn conn []
  "Gives a db connection handle"
  (d/connect db-uri))

(defn q-by-email [db email]
  "Takes `db`, `email` and pulls the entity
  for which the email matches"
  (d/query
    {:query '[:find (pull ?e [*])
              :in $ ?email
              :where [?e :person/email ?email]]
     :args  [db email]}))

(defn pull-by-uuid [db uuid]
  "Takes `db`, `uuid` instances and pulls entity from the db"
  (d/pull db '[*] [:uuid uuid]))

(defn read-edn [resource]
  (-> (io/resource resource) slurp edn/read-string))

(defn ensure-schema [conn]
  (d/transact conn (read-edn "schema.edn")))

(defn ensure-data [conn]
  (d/transact conn (read-edn "person-map-based.edn"))

  (defn -main
    "I don't do a whole lot ... yet."
    [& args]
    (println "Hello, World!")))
