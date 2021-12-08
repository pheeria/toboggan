(ns day-03-2021-test
  (:require
    [clojure.test :refer [deftest is testing]]
    [day-03-2021 :as toboggan]
    [clojure.java.io :as io]))


(def report
  '("00100"
    "11110"
    "10110"
    "10111"
    "10101"
    "01111"
    "00111"
    "11100"
    "10000"
    "11001"
    "00010"
    "01010"))

(def actual-input
  (with-open [rdr (io/reader (io/file "resources/2103.txt"))]
    (mapv identity (line-seq rdr))))

(deftest part-1
  (testing "With the example input"
    (is (= 198 (toboggan/binary-multiplication report)))
  (testing "With the actual input"
    (is (= 3958484 (toboggan/binary-multiplication actual-input))))))

(deftest part-2
  (testing "With the example input"
    (is (= 230 (toboggan/binary-multiplication report)))
  (testing "With the actual input"
    (is (= 1613181 (toboggan/binary-multiplication actual-input))))))
