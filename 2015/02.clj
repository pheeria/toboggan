(require '[clojure.string :refer [split split-lines]])

(defn to-dimensions [raw]
  (->> (split raw #"x")
       (map #(Integer/parseInt %))
       (zipmap [:l :w :h])))

(def input
  (->> "02.txt"
       slurp 
       split-lines
       (map to-dimensions)))

(defn calculate-paper [cuboid]
  (let [{length :l width :w height :h} cuboid
        a (* length width)
        b (* width height)
        c (* height length)
        smallest (min a b c)]
    (+ smallest a a b b c c)))

(defn calculate-ribbon [cuboid]
  (let [sides (vals cuboid)
        volume (apply * sides)
        shorter (take 2 (sort sides))
        perimeter (* 2 (apply + shorter))]
    (+ volume perimeter)))

(defn part-one [stream]
  (apply + (map calculate-paper stream)))

(defn part-two [stream]
  (apply + (map calculate-ribbon stream)))

(println (part-one input))
(println (part-two input))

