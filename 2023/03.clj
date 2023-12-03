(require '[clojure.string :as str])

(def input
  (-> "03.txt"
      slurp
      str/trim
      str/split-lines))

(defn next-number [pair]
  (let [processed (->> pair
                       (drop-while #(not (Character/isDigit (last %))))
                       (take-while #(Character/isDigit (last %))))
        number (->> processed
                    (map last)
                    (apply str))]
    (if (empty? number)
      {}
      {:number (Integer/parseInt number)
       :start (ffirst processed)
       :end (first (last processed))})))

(defn parse-numbers [text]
  (loop [line (map-indexed vector text)
         numbers []]
    (let [number (next-number line)]
      (if (empty? number)
        numbers
        (let [new-index (inc (:end number))
              new-line (drop-while #(<= (first %) new-index) line)
              new-numbers (conj numbers number)]
        (recur new-line new-numbers))))))

(defn constraints [coord max-coord]
  [(max 0 (dec coord))
   coord
   (min (dec max-coord) (inc coord))])

(defn valid-char? [stream row col]
  (let [ch (-> stream
               (nth row)
               (nth col))]
    (not (or (Character/isDigit ch)
             (= ch \.)))))

(defn valid-number? [stream row number]
  (let [maxrows (count stream)
        maxcols (count (first stream))
        [y-1 y y+1]   (constraints row maxrows)]
    (loop [col (:start number)
           valid? false]
      (if valid?
        (:number number)
        (if (> col (:end number))
          0
          (let [[x-1 x x+1] (constraints col maxcols)]
            (recur (inc col)
                   (or (valid-char? stream y-1 x-1)
                       (valid-char? stream y-1 x)
                       (valid-char? stream y-1 x+1)
                       (valid-char? stream y x-1)
                       (valid-char? stream y x+1)
                       (valid-char? stream y+1 x-1)
                       (valid-char? stream y+1 x)
                       (valid-char? stream y+1 x+1)))))))))

(defn valid-line? [stream row numbers]
  (->>
    numbers
    (map #(valid-number? stream row %))
    (apply +)))

(defn parse-stars [row text]
  (->> text
       (map-indexed vector)
       (filter #(= (last %) \*))
       (map #(hash-map :row row :col (first %)))))

(defn valid-star? [numbers star]
  (let [col (:col star)
        [y-1 y y+1] (constraints (:row star) (count numbers))
        all (concat (nth numbers y-1)
                    (nth numbers y)
                    (nth numbers y+1))
        filtered (->> all
                      (filter #(and (>= col (dec (:start %)))
                                    (<= col (inc (:end %)))))
                      (map :number))]
    (if (= (count filtered) 2)
      (apply * filtered)
      0)))

(defn part-one [stream]
  (->> stream
       (map-indexed #(hash-map :index %1 :numbers (parse-numbers %2)))
       (remove #(empty? (:numbers %)))
       (map #(valid-line? stream (:index %) (:numbers %)))
       (apply +)))

(defn part-two [stream]
  (let [numbers (map parse-numbers stream)]
    (->> stream
         (map-indexed #(parse-stars %1 %2))
         flatten
         (map #(valid-star? numbers %))
         (apply +))))

(println (part-one input))
(println (part-two input))
