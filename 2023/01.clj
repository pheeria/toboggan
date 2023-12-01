(require '[clojure.string :as str])

(def input
  (-> "01.txt"
      slurp
      str/trim
      str/split-lines))

(defn calibration [text]
  (let [digits (filter #(Character/isDigit %) text)
        one (first digits)
        two (last digits)]
    (Integer/parseInt (str one two))))

(defn notorious-calibration [text]
  (let [first-replaced (str/replace-first
                         text 
                         #"one|two|three|four|five|six|seven|eight|nine"
                         {"one" "1" "two" "2" "three" "3" "four" "4" "five" "5" "six" "6" "seven" "7" "eight" "8" "nine" "9"})
        last-replaced (str/replace-first
                         (apply str (reverse )


#"enin|thgie|neves|xis|evif|ruof|eerht|owt|eno"
{"neves" "7", "owt" "2", "xis" "6", "ruof" "4", "thgie" "8", "evif" "5", "enin" "9", "eerht" "3", "eno" "1"}
        digits (filter #(Character/isDigit %) replaced)
        one (first digits)
        two (last digits)]
    (Integer/parseInt (str one two))))

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

(part-one [
"1abc2"
"pqr3stu8vwx"
"a1b2c3d4e5f"
"treb7uchet"])
(part-two [
"two1nine"
"eightwothree"
"abcone2threexyz"
"xtwone3four"
"4nineeightseven2"
"zoneight234"
"7pqrstsixteen"
])

(map notorious-calibration [
"two1nine"
"eightwothree"
"abcone2threexyz"
"xtwone3four"
"4nineeightseven2"
"zoneight234"
"7pqrstsixteen"
])

(str/replace-first
                   "2one9five2"
                   #"one|two|three|four|five|six|seven|eight|nine"
                   {"one" "1" "two" "2" "three" "3" "four" "4" "five" "5" "six" "6" "seven" "7" "eight" "8" "nine" "9"})

#"enin|thgie|neves|xis|evif|ruof|eerht|owt|eno"
{"neves" "7", "owt" "2", "xis" "6", "ruof" "4", "thgie" "8", "evif" "5", "enin" "9", "eerht" "3", "eno" "1"}
(reduce #(assoc %1 (apply str (reverse (first %2))) (last %2)) {} {"one" "1" "two" "2" "three" "3" "four" "4" "five" "5" "six" "6" "seven" "7" "eight" "8" "nine" "9"})

(map identity {"one" "1" "two" "2" "three" "3" "four" "4" "five" "5" "six" "6" "seven" "7" "eight" "8" "nine" "9"})

(apply str (reverse "one|two|three|four|five|six|seven|eight|nine"))

{
 "one" 1, "1" 1,
 "two" 2, "2" 2,
 "three" 3, "3" 3,
 "four" 4, "4" 4,
 "five" 5, "5" 5,
 "six" 6, "6" 6,
 "seven" 7, "7" 7,
 "eight" 8, "8" 8,
 "nine" 9, "9" 9
}
