(ns examples.fixtures
  (:require [clojure.test :refer :all]
            [examples.db :as db]))

(defn with-db [f]
  (let [_ (db/create-db)
        conn (db/conn)]
    (db/ensure-schema conn)
    (db/ensure-data conn)
    (f)
    (db/delete-db)))
