(ns day-01-2021-test
  (:require
    [clojure.test :refer [deftest is testing]]
    [day-01-2021 :as toboggan]
    [clojure.java.io :as io]))


(def depths
  '(199
    200
    208
    210
    200
    207
    240
    269
    260
    263))

(def actual-input
  (with-open [rdr (io/reader (io/file "resources/2101.txt"))]
    (mapv #(Integer/parseInt %) (line-seq rdr))))

(deftest part-1
  (testing "With the example input"
    (is (= 7 (toboggan/number-of-increases depths 2)))
  (testing "With the actual input"
    (is (= 1559 (toboggan/number-of-increases actual-input 2))))))

(deftest part-2
  (testing "With the example input"
    (is (= 5 (toboggan/number-of-increases depths 4)))
  (testing "With the actual input"
    (is (= 1600 (toboggan/number-of-increases actual-input 4))))))
