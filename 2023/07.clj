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
    (if (not= ath bth)
      (compare ath bth)
      (compare-cards (:card a) (:card b)))))

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
    {:card card :freqs freqs :bid bid :hand hand}))

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

(defn compare-joker-cards [[a & as] [b & bs]]
  (if (nil? a)
    0
    (let [a-order (get joker-order a)
          b-order (get joker-order b)]
      (if (= a-order b-order)
        (compare-cards as bs)
        (compare a-order b-order)))))

(defn custom-joker-compare [a b]
  (let [ath (get types (:hand a))
        bth (get types (:hand b))]
    (if (not= ath bth)
      (compare ath bth)
      (compare-joker-cards (:card a) (:card b)))))

(defn parse-joker-card [line]
  (let [[card raw-bid] (str/split line #" ") 
        freqs (frequencies card)
        bid (Integer/parseInt raw-bid)
        jokers (get freqs \J 0)
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
    {:card card :freqs freqs :bid bid :hand hand}))
(defn part-one [stream]
  (let [length (count stream)
        cards (map parse-card stream)
        sorted (sort custom-compare cards)
        indexed (map-indexed #(* (- length %1) (:bid %2)) sorted)]
  (apply + indexed)))

(defn part-two [stream]
  (let [length (count stream)
        cards (map parse-joker-card stream)
        sorted (sort custom-joker-compare cards)
        indexed (map-indexed #(* (- length %1) (:bid %2)) sorted)]
  (apply + indexed)))

(println (part-one input))
(println (part-two input))

(part-two [
"32T3K 765"
"T55J5 684"
"KK677 28"
"KTJJT 220"
"QQQJA 483"
])

(part-one [
"32T3K 765"
"T55J5 684"
"KK677 28"
"KTJJT 220"
"QQQJA 483"
])
(part-two "")







(sort #(loop [[a & as] %1
              [b & bs] %2]
         (let [a-order (get order a)
               b-order (get order b)]
           (println a-order b-order)
           (println as bs)
           (if (= a-order b-order)
             (recur as bs)
             (compare a-order b-order)))
         ) '("A7QTK" "A89TQ"))

(sort #(do (let [[a & as] %1
                 [b & bs] %2]
             (println (get order a))
             (println (get order b))
             (println as bs)
             )
           (compare %1 %2))
         '("A7QTK" "A89TQ"))

(compare-cards "A7QTK" "A89TQ")

(= '() '())

(sort custom-compare '({:card "32T3K", :hand :full-house} {:card "32T3K", :hand :five} {:card "32T3A", :hand :five}))
