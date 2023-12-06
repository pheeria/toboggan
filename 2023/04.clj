(require '[clojure.string :as str]
         '[clojure.set :as set]
         '[clojure.math :as math])

(def input
  (-> "04.txt"
      slurp
      str/trim
      str/split-lines))

(defn parse-line [text]
  (let [[card numbers] (str/split text #":\s+")
        all (str/split numbers #" | ")
        winning (->> all
                     (take-while #(not (= "|" %)))
                     set)
        elf's (->> all
                     (drop-while #(not (= "|" %)))
                     rest
                     set)
        found (count 
                (remove #(or (= " " %) (empty? %))
                  (set/intersection winning elf's)))]

    (if (= found 0)
      0
      (->> (dec found)
           (math/pow 2)
           int)
    )))

(defn parse-card [text]
  (let [[card numbers] (str/split text #":\s+")
        all (str/split numbers #" | ")
        winning (->> all
                     (take-while #(not (= "|" %)))
                     set)
        elf's (->> all
                     (drop-while #(not (= "|" %)))
                     rest
                     set)]
        (count 
          (remove #(or (= " " %) (empty? %))
                  (set/intersection winning elf's)))))

(defn copy-cards [cards index {value :value copies :copies}]
  (loop [cs cards
         new-index (inc index)]
      (if (<= (- new-index index) value)
        (recur (update-in cs [new-index :copies] + copies)
               (inc new-index))
        cs)))

(defn part-one [stream]
  (->> stream
       (map parse-line)
       (apply +)))

(defn part-two [stream]
  (let [cards (mapv #(hash-map :value (parse-card %) :copies 1)
                    stream)
        final (reduce #(copy-cards %1 %2 (get %1 %2))
                      cards
                      (range (count cards)))]
    (apply + (map :copies final))))

(println (part-one input))
(println (part-two input))
