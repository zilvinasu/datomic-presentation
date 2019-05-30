(ns examples.query
  (:require [datomic.api :as d]))

(defn person-by-email [db email]
  "Takes `db`, `email` and pulls the entity
  for which the email matches"
  (d/q
    '[:find ?e
      :in $ ?email
      :where [?e :person/email ?email]]
    db email))


(defn pull-by-email [db email]
  "Takes `db`, `uuid` instances and pulls entity from the db"
  (d/pull
    db
    '[:person/first-name :person/last-name :person/email]
    [:person/email email]))