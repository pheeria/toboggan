(require '[clojure.string :as str])
(import java.security.MessageDigest
        java.math.BigInteger)

(def input
  (->> "04.txt"
      slurp
      str/trim
      str/split-lines
      first))

(defn md5 [s]
  (->> s
       .getBytes
       (.digest (MessageDigest/getInstance "MD5"))
       (BigInteger. 1)
       (format "%032x")))

(defn starts-with-zeroes? [hashed counter zeroes]
  (-> (str hashed counter)
       md5
       (str/starts-with? zeroes)))

(defn part-one [hashed]
  (loop [counter 100000]
    (if (starts-with-zeroes? hashed counter "00000")
      counter
      (recur (inc counter)))))

(defn part-two [hashed]
  (loop [counter 100000]
    (if (starts-with-zeroes? hashed counter "000000")
      counter
      (recur (inc counter)))))

(println (part-one input))
(println (part-two input))

