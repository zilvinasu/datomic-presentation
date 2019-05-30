(ns examples.db
  (:require [datomic.api :as d]))

(def ^:private db-uri "datomic:mem://vilnius-clojure")

(defn empty-db-conn []
  "Creates empty in-memory db and returns the connection"
  (d/delete-database db-uri)
  (d/create-database db-uri)
  (d/connect db-uri))

(defn conn []
  "Gives a db connection handle"
  (d/connect db-uri))

(defn ensure-schema [conn]
  (d/transact conn (read-edn "schema.edn")))

(defn ensure-data [conn]
  (d/transact conn (read-edn "person-map-based.edn")))

(defn has-attribute? [conn attribute]
  "Takes `conn` and `attribute`
  then checks whether it is in db"
  (boolean (d/entity (d/db conn) attribute)))
