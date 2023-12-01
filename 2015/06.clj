(require '[clojure.string :as str]
         '[clojure.pprint :refer [pprint]])

(defn parse-op [text]
  (let [t (str/replace text #" through " " ")
        [op tt] (cond
                  (str/starts-with? t "toggle")   [:toggle (str/replace t #"toggle " "")]
                  (str/starts-with? t "turn on")  [:on (str/replace t #"turn on " "")]
                  (str/starts-with? t "turn off") [:off (str/replace t #"turn off " "")])
        parsed-coords (->> (str/split tt #" ")
                        (map #(str/split % #","))
                        flatten
                        (map #(Integer/parseInt %))
                        (zipmap [:fromx :fromy :tox :toy]))]
  (into parsed-coords {:op op})))

(def input
  (-> "06.txt"
      slurp
      str/trim
      str/split-lines))

(def grid
  (into {}
        (zipmap (take 5 (range))
                (repeat (into {}
                              (zipmap (take 5 (range))
                                      (repeat false)))))))

(defn count-in-grid [g]
  (->> g
       vals
       (map #(count (filter true? %)))
       ))

(pprint (vals (flatten (vals grid))))
(pprint (assoc-in grid [2 2] true))
(pprint (count-in-grid (assoc-in grid [2 2] true)))

(defn part-one [stream]

)

(defn part-two [stream]

)

(println (part-one input))
(println (part-two input))

(part-one "")
(part-two "")

(parse-op (first input))


toggle 461,550 through 564,900
turn off 370,39 through 425,839
turn off 464,858 through 833,915
