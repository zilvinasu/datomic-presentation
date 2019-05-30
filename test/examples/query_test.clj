(ns examples.query-test
  (:require
    [clojure.test :refer :all]
    [examples.query :refer [pull-by-uuid]]
    [examples.db :as db]
    [examples.query :as q]
    [examples.fixtures :refer [with-db]]))

(use-fixtures :each with-db)

(deftest query-api-test
  (let [db (db/current-db)]
    (testing "find person by name"
      (is (ffirst (q/person-by-email db "jenkins@mailinator.com")))
      (is (= (q/pull-by-email db "mcnugget@mailinator.com")
             #:person {:email      "mcnugget@mailinator.com"
                       :first-name "JSON"
                       :last-name  "McNugget"})))))