(ns examples.schema-test
  (:require [clojure.test :refer :all]
            [datomic.api :as d]
            [examples.fixtures :refer [with-db]]
            [examples.db :as db]
            [examples.query :as q]))

(use-fixtures :each with-db)

(deftest db-functions-test
  (testing "normalize person's last name"
    (let [updated-db (:db-after
                      (d/with
                        (db/current-db)
                        [[:person/normalize-last-name "mcnugget@mailinator.com"]]))]
     (is (= (:person/last-name (q/pull-by-email updated-db "mcnugget@mailinator.com"))
            "MCNUGGET")))))
