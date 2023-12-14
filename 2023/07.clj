(require '[clojure.string :as str])

(def input
  (-> "07.txt"
      slurp
      str/trim
      str/split-lines))

(def types {
  :five 1
  :four 2
  :full-house 3
  :three 4
  :two-pair 5
  :one-pair 6
  :high 7
})

(def order {
  \A 1
  \K 2
  \Q 3
  \J 4
  \T 5
  \9 6
  \8 7
  \7 8
  \6 9
  \5 10
  \4 11
  \3 12
  \2 13
})

(defn compare-cards [[a & as] [b & bs]]
  (if (nil? a)
    0
    (let [a-order (get order a)
          b-order (get order b)]
      (if (= a-order b-order)
        (compare-cards as bs)
        (compare a-order b-order)))))

(defn custom-compare [a b]
  (let [ath (get types (:hand a))
        bth (get types (:hand b))]
    (if (= ath bth)
      (compare-cards (:card a) (:card b))
      (compare ath bth))))

(defn parse-card [line]
  (let [[card raw-bid] (str/split line #" ") 
        freqs (frequencies card)
        bid (Integer/parseInt raw-bid)
        sorted (sort (vals freqs))
        hand (cond
               (= '(5) sorted) :five
               (= '(1 4) sorted) :four
               (= '(2 3) sorted) :full-house
               (= '(1 1 3) sorted) :three
               (= '(1 2 2) sorted) :two-pair
               (= '(1 1 1 2) sorted) :one-pair
               (every? #(= 1 %) sorted) :high)
        ]
    {:card card :bid bid :hand hand}))

(def joker-order {
  \A 1
  \K 2
  \Q 3
  \T 4
  \9 5
  \8 6
  \7 7
  \6 8
  \5 9
  \4 10
  \3 11
  \2 12
  \J 13
})

(defn custom-joker-compare [a b]
  (let [ath (get types (:hand a))
        bth (get types (:hand b))]
    (if (not= ath bth)
      (compare ath bth)
      (loop [[a & as] (:card a)
             [b & bs] (:card b)]
        (if (nil? a)
          0
          (let [a-order (get joker-order a)
                b-order (get joker-order b)]
            (if (= a-order b-order)
              (recur as bs)
              (compare a-order b-order))))))))

(defn parse-joker-card [line]
  (let [[card raw-bid] (str/split line #" ") 
        freqs (frequencies card)
        bid (Integer/parseInt raw-bid)
        sorted (sort (vals (dissoc freqs \J)))
        hand (cond
               (= '() sorted) :five
               (= '(5) sorted) :five

               (= '(4) sorted) :five
               (= '(1 4) sorted) :four

               (= '(3) sorted) :five
               (= '(1 3) sorted) :four
               (= '(2 3) sorted) :full-house
               (= '(1 1 3) sorted) :three

               (= '(2) sorted) :five
               (= '(1 2) sorted) :four
               (= '(2 2) sorted) :full-house
               (= '(1 1 2) sorted) :three
               (= '(1 2 2) sorted) :two-pair

               (= '(1) sorted) :five
               (= '(1 1) sorted) :four
               (= '(1 1 1) sorted) :three
               (= '(1 1 1 1) sorted) :one-pair
               (= '(1 1 1 2) sorted) :one-pair
               (= '(1 1 1 1 1) sorted) :high
        )]
    {:card card :bid bid :hand hand}))

(defn part-one [stream]
  (let [length (count stream)]
    (->> stream
         (map parse-card)
         (sort custom-compare)
         (map-indexed #(* (- length %1) (:bid %2)))
         (apply +))))

(defn part-two [stream]
  (let [length (count stream)]
    (->> stream
         (map parse-joker-card)
         (sort custom-joker-compare)
         (map-indexed #(* (- length %1) (:bid %2)))
         (apply +))))

(println (part-one input))
(println (part-two input))

