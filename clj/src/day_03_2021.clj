(ns day-03-2021)

(defn binary-multiplication
  "Counts number of occurences and multiplies them."
  [binaries]
  (let [width (->> binaries
                   first
                   count)
        dict (->> width
                  range
                  (into {} (map (fn [i] {i {0 0 1 0}}))))
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

(defn second-binary-multiplication
  "Counts number of occurences and multiplies them."
  [binaries]
  (defn get-rating [binaries width predicate]
    (let [result (reduce (fn [filtered-coll position]
                           (if (= 1 (count filtered-coll))
                             (reduced (first filtered-coll))
                             (let [chars (map #(nth % position) filtered-coll)
                                   {zeroes \0 ones \1} (frequencies chars)
                                   matching-char (if (predicate zeroes ones) \0 \1)]
                               (filter #(= matching-char (nth % position)) filtered-coll)))) binaries (range width))]
      (if (seq? result) (first result) result)))
  (let [width    (->> binaries first count)
        oxygen   (get-rating binaries width >)
        co-2   (get-rating binaries width <=)]
    (* (Long/parseLong oxygen 2) (Long/parseLong co-2 2))))
