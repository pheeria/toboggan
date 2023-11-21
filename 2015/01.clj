(require '[clojure.string :refer [trim]])

(def input (trim (slurp "01.txt")))

(defn part-one [stream]
  (loop [counter 0
         data stream]
    (if (empty? data)
      counter
      (if (= (first data) \()
           (recur (inc counter) (rest data))
           (recur (dec counter) (rest data))))))

(defn part-one-alternative [stream]
  (let [parens (frequencies stream)]
    (- (get parens \() (get parens \)))))


(defn part-two [stream]
  (loop [counter 0
         position 0
         data stream]
    (if (= counter -1)
      position
      (if (= (first data) \()
           (recur (inc counter) (inc position) (rest data))
           (recur (dec counter) (inc position) (rest data))))))


(println (part-one input))
(println (part-one-alternative input))
(println (part-two input))
