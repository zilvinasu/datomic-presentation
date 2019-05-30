(ns examples.query
  (:require [datomic.api :as d]))


(defn person-by-email [db email]
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