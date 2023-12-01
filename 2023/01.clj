(require '[clojure.string :as str])

(def input
  (-> "01.txt"
      slurp
      str/trim
      str/split-lines))

(def cases {
 "one" 1, "1" 1,
 "two" 2, "2" 2,
 "three" 3, "3" 3,
 "four" 4, "4" 4,
 "five" 5, "5" 5,
 "six" 6, "6" 6,
 "seven" 7, "7" 7,
 "eight" 8, "8" 8,
 "nine" 9, "9" 9
})

(defn calibration [text]
  (let [digits (filter #(Character/isDigit %) text)
        one (first digits)
        two (last digits)]
    (Integer/parseInt (str one two))))

(defn notorious-calibration [text]
  (let [all-keys (keys cases)
        first-digit (->>
                       all-keys
                       (map #(list (get cases %) (str/index-of text %)))
                       (remove #(nil? (last %)))
                       (sort-by last)
                       ffirst)
        last-digit (->>
                       all-keys
                       (map #(list (get cases %) (str/last-index-of text %)))
                       (remove #(nil? (last %)))
                       (sort-by last >)
                       ffirst)]
    (Integer/parseInt (str first-digit last-digit))))
                        
(defn part-one [stream]
  (->> stream
       (map calibration)
       (apply +)))

(defn part-two [stream]
  (->> stream
       (map notorious-calibration)
       (apply +)))

(println (part-one input))
(println (part-two input))
