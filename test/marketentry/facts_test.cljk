(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest col-has-spec-basis
  (let [sb (facts/spec-basis "COL")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/rep-spec-basis "COL")))
    (is (some? (facts/corporate-number-spec-basis "COL")))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "COL")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "COL" all)))
    (is (not (facts/required-evidence-satisfied? "COL" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["COL" "ATL" "ZZZ"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "ZZZ"] (:missing-jurisdictions c)))))
