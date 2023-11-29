(require '[clojure.string :as str])

(def input
  (-> "05.txt"
      slurp
      str/trim
      str/split-lines))

(defn nice? [text]
  (every? false?
      (map
        #(str/includes? text %)
        (list "ab" "cd" "pq" "xy"))))

(defn three-vowels? [text]
  (let [freqs (frequencies text)
        vowels "aeiou"]
    (> (reduce 
           #(+ %1 (get freqs %2 0))
           0
           vowels)
       2)))

(defn twice-in-a-row? [text]
  (->> text
       (partition 2 1)
       (map #(= (first %) (last %)))
       (some true?)))

(defn pair? [text]
  (loop [index 0
         seen {}
         [group & groups] (partition 2 1 text)]
    (if (> (- index (get seen group index)) 1)
      true
      (if (nil? groups)
        false
        (recur (inc index)
               (if (contains? seen group)
                   seen
                   (assoc seen group index)) 
               groups)))))

(defn triplet? [text]
  (->> text
       (partition 3 1)
       (filter #(= (first %) (last %)))
       count
       (< 0)))

(defn part-one [stream]
  (->> stream
       (filter (every-pred 
                 three-vowels?
                 nice?
                 twice-in-a-row?))
        count))

(defn part-two [stream]
  (->> stream
       (filter (every-pred 
                 pair?
                 triplet?))
        count))

(println (part-one input))
(println (part-two input))
