(ns day-02-2021-test
  (:require
    [clojure.test :refer [deftest is testing]]
    [day-02-2021 :as toboggan]
    [clojure.string :as string]
    [clojure.java.io :as io]))


(def commands
  '(
    {:direction "forward" :units 5}
    {:direction "down" :units 5}
    {:direction "forward" :units 8}
    {:direction "up" :units 3}
    {:direction "down" :units 8}
    {:direction "forward" :units 2}
))


(def actual-input
  (with-open [rdr (io/reader (io/file "resources/2102.txt"))]
    (mapv (fn [input]
            (as-> input line 
                 (string/split line #" ")
                 (assoc {} 
                        :direction (first line)
                        :units (Integer/parseInt (last line)))))
          (line-seq rdr))))

(deftest part-1
  (testing "With the example input"
    (is (= 150 (toboggan/calculate-position commands)))
  (testing "With the actual input"
    (is (= 1427868 (toboggan/calculate-position actual-input))))))

(deftest part-2
  (testing "With the example input"
    (is (= 900 (toboggan/calculate-position-with-aim commands)))
  (testing "With the actual input"
    (is (= 1568138742 (toboggan/calculate-position-with-aim actual-input))))))
