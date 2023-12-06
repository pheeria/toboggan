(require '[clojure.string :as str])

(def input
  (-> "06.txt"
      slurp
      str/trim
      str/split-lines))

(defn parse-line [line]
  (->> line
       (re-seq #"\d+")
       (map #(Integer/parseInt %))))

(defn options [time]
  (->> (range (inc time))
       (map #(* % (- time %)))))

(defn part-one [stream]
  (let [times (parse-line (first stream))
        distances (parse-line (last stream))
        opts (map options times)
        valids (map (fn [[k v]]
                    (filter #(> % k) v)) (zipmap distances opts))]
    (apply * (map count valids))))

(defn parse-race [line]
  (Long/valueOf (apply str (parse-line line))))

(defn part-two [stream]
  (let [time (parse-race (first stream))
        distance (parse-race (last stream))
        opts (options time)
        valids (filter #(> % distance) opts)]
    (count valids)
    ))

(println (part-one input))
(println (part-two input))

