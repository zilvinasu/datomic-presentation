(ns examples.db
  (:require [datomic.api :as d]
            [examples.utils :refer [resource->edn]]))

(def ^:private db-uri "datomic:mem://vilnius-clojure")

(defn create-db []
  (d/create-database db-uri))

(defn delete-db []
  (d/delete-database db-uri))

(defn conn []
  "Gives a db connection handle"
  (d/connect db-uri))

(defn current-db []
  "Gets the current db value"
  (d/db (conn)))

(defn ensure-schema [conn]
  (d/transact conn (resource->edn "schema.edn")))

(defn ensure-data [conn]
  (d/transact conn (resource->edn "person-map-based.edn")))

(defn has-attribute? [conn attribute]
  "Takes `conn` and `attribute`
  then checks whether it is in db"
  (boolean (d/entity (d/db conn) attribute)))
