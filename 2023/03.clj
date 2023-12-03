(require '[clojure.string :as str]
         '[clojure.pprint :refer [pprint]])

(def input
  (-> "03.txt"
      slurp
      str/trim
      str/split-lines))

(defn next-number-pair [pair]
  (let [processed (->> pair
                       (drop-while #(not (Character/isDigit (last %))))
                       (take-while #(Character/isDigit (last %))))
        start (ffirst processed)
        end   (first (last processed))
        number (->> processed
                    (map last)
                    (apply str))]
    (if (empty? number)
      {}
      {:number (Integer/parseInt number) :start start :end end})))

(defn parse-line [text]
  (loop [line (map-indexed vector text)
         numbers []]
    (let [number (next-number-pair line)]
      (if (empty? number)
        numbers
        (let [new-index (inc (:end number))
              new-numbers (conj numbers number)
              new-line (drop-while #(<= (first %) new-index) line)]
        (recur new-line new-numbers))))))

(defn val-at [stream row col]
  (-> stream
      (nth row)
      (nth col)))

(defn constraints [coord max-coord]
  [(max 0 (dec coord))
   coord
   (min (dec max-coord) (inc coord))])

(defn valid-char? [ch]
  (not (or (Character/isDigit ch) (= ch \.))))

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
                   (or (valid-char? (val-at stream y-1 x-1))
                       (valid-char? (val-at stream y-1 x))
                       (valid-char? (val-at stream y-1 x+1))
                       (valid-char? (val-at stream y x-1))
                       (valid-char? (val-at stream y x+1))
                       (valid-char? (val-at stream y+1 x-1))
                       (valid-char? (val-at stream y+1 x))
                       (valid-char? (val-at stream y+1 x+1))))))))))

(defn valid-line? [stream row numbers]
  (->>
    numbers
    (map #(valid-number? stream row %))
    (apply +)))

(defn part-one [stream]
  (->> stream
       (map-indexed #(hash-map :index %1 :numbers (parse-line %2)))
       (remove #(empty? (:numbers %)))
       (map #(valid-line? stream (:index %) (:numbers %)))
       (apply +)))

(defn part-two [stream]

)

(println (part-one input))
(println (part-two input))

 
(parse-line "..35..633.")

(valid-line? [
"467..114.."
"...*......"
"..35..633."
"......#..."
"617*......"
".....+.58."
"..592....."
"......755."
"...$.*...."
".664.598.."
] 0 [{:number 467, :start 0, :end 2} {:number 114, :start 5, :end 7}])

(count [
"467..114.."
"...*......"
"..35..633."
"......#..."
"617*......"
".....+.58."
"..592....."
"......755."
"...$.*...."
".664.598.."
])

(part-one [
"467..114.."
"...*......"
"..35..633."
"......#..."
"617*......"
".....+.58."
"..592....."
"......755."
"...$.*...."
".664.598.."
])
(part-two "")


