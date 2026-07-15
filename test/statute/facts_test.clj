(ns statute.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest col-has-spec-basis
  (let [sb (facts/spec-basis "COL")]
    (is (= 3 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://www.funcionpublica.gov.co/") sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["COL" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["col.codigo-sustantivo-trabajo-decreto-2663-1950"]
         (mapv :statute/id (facts/by-topic "COL" :labor))))
  (is (empty? (facts/by-topic "COL" :environment)))
  (is (empty? (facts/by-topic "ATL" :labor))))
