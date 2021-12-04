(ns day-01-2021)

(defn number-of-increases
  "Counts the number of times a depth measurement increases from the previous one"
  [numbers partition-size]
  (count 
    (filter pos?
            (map 
              #(- (last %) (first %)) 
              (partition partition-size 1 numbers)))))

