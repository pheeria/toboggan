(require '[clojure.string :as str])

(def input
  (-> "02.txt"
      slurp
      str/trim
      str/split-lines))

(defn rgb [text]
  (let [by-comma (str/split text #", ")
        by-space (map #(str/split % #" ") by-comma)
        red (let [reds (filter #(= (last %) "red") by-space)]
             (if (empty? reds)
              0
              (Integer/parseInt (ffirst reds))))
        green (let [greens (filter #(= (last %) "green") by-space)]
               (if (empty? greens)
                0
                (Integer/parseInt (ffirst greens))))
        blue (let [blues (filter #(= (last %) "blue") by-space)]
               (if (empty? blues)
                0
                (Integer/parseInt (ffirst blues))))]
    {"red" red "green" green "blue" blue}
    ))

(defn game [text]
  (let [by-colon (str/split text #": ")
        gid (Integer/parseInt (subs (first by-colon) 5))
        by-semicolon (str/split (last by-colon) #"; ")
        rgbs (map rgb by-semicolon)
        filtered (filter #(and (<= (get % "red") 12)
                               (<= (get % "green") 13)
                               (<= (get % "blue") 14)) rgbs)
        gid-to-add (if (= (count filtered) (count rgbs))
                       gid
                       0)]
    (println filtered)
    gid-to-add))

(defn game-two [text]
  (let [by-colon (str/split text #": ")
        by-semicolon (str/split (last by-colon) #"; ")
        rgbs (map rgb by-semicolon)
        merged (apply merge-with max rgbs)
        multiplied (apply * (vals merged))]
    multiplied))

(defn part-one [stream]
  (->> stream
       (map game)
       (apply +)))

(defn part-two [stream]
  (->> stream
       (map game-two)
       (apply +)))

(println (part-one input))
(println (part-two input))

(part-one [
"Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
"Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"
"Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"
"Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"
"Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
])

(part-two [
"Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
"Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"
"Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"
"Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"
"Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
])
(subs (first (str/split (first input) #":")) 5)

(game (first input))
(game "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
(game "Game 97: 3 green, 3 blue; 5 green, 3 blue, 1 red; 5 green, 1 red, 3 blue; 1 green, 1 red, 2 blue; 2 green, 2 blue, 3 red")
(rgb "1 blue, 9 red")
(empty? (filter #(= (last %) "red") ["1 blue" "9 red"]))

