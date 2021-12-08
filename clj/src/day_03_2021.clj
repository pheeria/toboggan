(ns day-03-2021)

(defn binary-multiplication
  "Counts number of occurences and multiplies them."
  [binaries]
  (let [width (->> binaries
                  first
                  count)
        dict (->> width
                  range
                  (into {} (map (fn [i] { i { 0 0 1 0 }}))))
        occurences (reduce 
                     (fn [dic binary]
                       (let [pairs (map-indexed
                                     (fn [idx chr] 
                                          [idx (Character/digit chr 10)]) binary)]
                            (reduce (fn [dc p] (update-in dc p inc))
                                    dic
                                    pairs)))
                     dict
                     binaries)
        gamma (apply + (map (fn [[i dic]]
                        (let [bin (->> dic
                                      (sort-by val >)
                                      first
                                      key)]
                        (* bin (int (Math/pow 2 (- width i 1))))))
                              occurences))
        epsilon (apply + (map (fn [[i dic]]
                        (let [bin (->> dic
                                      (sort-by val <)
                                      first
                                      key)]
                        (* bin (int (Math/pow 2 (- width i 1))))))
                              occurences))]
  (* gamma epsilon)))

