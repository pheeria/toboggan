(require '[clojure.string :as str])

(def input
  (-> "02.txt"
      slurp
      str/trim
      str/split-lines))

(defn cubes [color combi]
  (let [colors (filter #(= (last %) color) combi)]
   (if (empty? colors)
       0
       (Integer/parseInt (ffirst colors)))))

(defn rgb [text]
  (let [by-comma (str/split text #", ")
        by-space (map #(str/split % #" ") by-comma)
        red (cubes "red" by-space)
        green (cubes "green" by-space)
        blue (cubes "blue" by-space)]
    {"red" red "green" green "blue" blue}))

(defn game-one [text]
  (let [by-colon (str/split text #": ")
        by-semicolon (str/split (last by-colon) #"; ")
        rgbs (map rgb by-semicolon)
        filtered (filter #(and (<= (get % "red") 12)
                               (<= (get % "green") 13)
                               (<= (get % "blue") 14)) rgbs)
        gid (Integer/parseInt (subs (first by-colon) 5))]
    (if (= (count filtered) (count rgbs))
        gid
        0)))

(defn game-two [text]
  (let [by-colon (str/split text #": ")
        by-semicolon (str/split (last by-colon) #"; ")
        rgbs (map rgb by-semicolon)
        merged (apply merge-with max rgbs)]
      (apply * (vals merged))))

(defn part-one [stream]
  (->> stream
       (map game-one)
       (apply +)))

(defn part-two [stream]
  (->> stream
       (map game-two)
       (apply +)))

(println (part-one input))
(println (part-two input))

