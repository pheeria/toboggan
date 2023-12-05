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

(defn copy-cards [cards index current]
  (loop [cs cards
         new-index (inc index)]
    (let [value (:value current)
          copies (:copies current)]
      (if (<= (- new-index index) value)
        (recur (update-in cs [new-index :copies] + copies)
               (inc new-index))
        cs))))

(defn part-one [stream]
  (->> stream
       (map parse-line)
       (apply +)))

(defn part-two [stream]
  (let [cards (map parse-card stream)
        maps (map-indexed #(vector %1 (hash-map :value %2 :copies 1)) cards)
        dict (into {} maps)
        final (reduce (fn [acc cur]
                          (copy-cards acc cur (get acc cur))) dict (range (count cards)))]
    (apply + (map :copies (vals final)))))

(println (part-one input))
(println (part-two input))

