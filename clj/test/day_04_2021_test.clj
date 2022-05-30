(ns day-04-2021-test
  (:require
    [clojure.test :refer [deftest is testing]]
    [day-04-2021 :as toboggan]
    [clojure.string :as str]
    [clojure.java.io :as io]))

(def actual-input
  (with-open [rdr (io/reader (io/file "resources/2101.txt"))]
    (mapv #(Integer/parseInt %) (line-seq rdr))))

(def game
'("7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1"
  ""
  "22 13 17 11  0"
  " 8  2 23  4 24"
  "21  9 14 16  7"
  " 6 10  3 18  5"
  " 1 12 20 15 19"
  ""
  " 3 15  0  2 22"
  " 9 18 13 17  5"
  "19  8  7 25 23"
  "20 11 10 24  4"
  "14 21 16 12  6"
  ""
  "14 21 17 24  4"
  "10 16 15  9 19"
  "18  8 23 26 20"
  "22 11 13  6  5"
  " 2  0 12  3  7"))

(defn parse-game [game]
  (let [draws (mapv
                #(Integer/parseInt %)
                (str/split (first game) #","))
        lines (drop 2 game)
        boards (loop [board [] result [] lines lines]
                 (if (empty? lines)
                   (conj result board)
                   (let [first-line (first lines)]
                     (if (empty? first-line)
                       (recur [] (conj result board) (rest lines))
                       (recur (conj board
                                    (mapv (fn [s] {:value (Integer/parseInt s) :seen? false})
                                          (filter not-empty
                                                  (str/split first-line #" "))))
                              result (rest lines))))))]
    [draws boards]))


(deftest part-1
  (testing "With the example input"
    (is (= 7 (toboggan/bingo (parse-game game))))
  (testing "With the actual input"
    (is (= 1559 (toboggan/bingo (parse-game actual-input)))))))

(deftest part-2
  (testing "With the example input"
    (is (= 5 (toboggan/bingo (parse-game game))))
  (testing "With the actual input"
    (is (= 1600 (toboggan/bingo (parse-game game)))))))
