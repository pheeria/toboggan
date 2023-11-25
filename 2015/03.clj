(require '[clojure.string :as str])

(def input (str/trim (slurp "03.txt")))

(defn move [ch position]
  (case ch
       \^ (update position :y inc)
       \v (update position :y dec)
       \> (update position :x inc)
       \< (update position :x dec)))

(defn visited-positions [stream]
  (loop [[ch & chrs] stream
         position {:x 0 :y 0}
         visited #{position}]
    (let [new-position (move ch position)
          new-visited  (conj visited new-position)]
      (if (empty? chrs)
        new-visited
        (recur chrs new-position new-visited)))))

(defn part-one [stream]
  (count (visited-positions stream)))

(defn part-two [stream]
  (let [santa (visited-positions (take-nth 2 stream))
        robo  (visited-positions (take-nth 2 (rest stream)))]
    (count (into santa robo))))

(println (part-one input))
(println (part-two input))

