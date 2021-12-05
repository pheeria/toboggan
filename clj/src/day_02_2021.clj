(ns day-02-2021)

(defn calculate-position
  "Calculates final coordinates after following the instructions."
  [commands]
  (let [position
          (reduce (fn [pos {:keys [direction units]}]
            (case direction
              "forward" (assoc pos
                              :x (+ (:x pos) units))
              "up"      (assoc pos
                              :y (- (:y pos) units))
              "down"    (assoc pos
                              :y (+ (:y pos) units))))
            {:x 0 :y 0} commands)]
  (* (:x position) (:y position))))

(defn calculate-position-with-aim
  "Calculates final coordinates after following the instructions."
  [commands]
  (let [position
          (reduce (fn [pos {:keys [direction units]}]
            (case direction
              "forward" 
                        (assoc pos
                              :x (+ (:x pos) units)
                              :y (+ (:y pos)
                                    (* (:aim pos) units)))
              "up"      (assoc pos
                              :aim (- (:aim pos) units))
              "down"    (assoc pos
                              :aim (+ (:aim pos) units))))
            {:x 0 :y 0 :aim 0} commands)]
  (* (:x position) (:y position))))

