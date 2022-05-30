(ns day-04-2021)

(defn bingo
  "Counts the number of times a depth measurement increases from the previous one"
  [[draws boards]]
  (loop [draws draws]
    
    (recur (rest draws)))
  (count boards))


(defn bingo? [board]
  (let [x (count (first board))
        y (count board)]
    (if (= x (get (mapv #(frequencies (map :seen? %)) board)
                  true
                  0))
      true
      (mapv #(frequencies (map :seen? %)) board)
    )))

(bingo?
        [[
          { :value 1 :seen? true }
          { :value 2 :seen? true }
          { :value 3 :seen? true }
          { :value 4 :seen? true }
          { :value 5 :seen? true }
           ]])

