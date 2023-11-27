#! /usr/bin/env bash

if [ -z "$1" ]; then
    echo "Year missing!"
    exit 1
fi

if [ -z "$2" ]; then
    echo "Day missing!"
    exit 1
fi

YEAR="${1}"
DAY="${2}"
if [ ${DAY} -lt 10 ]; then
    DAY="0${DAY}"
fi

# use ${2} directly, since days in AoC are normal
# but we store them zero prefixed
curl --silent --location "https://adventofcode.com/${YEAR}/day/${2}/input" \
    --cookie "session=${AOC_TOKEN}" --create-dirs --output "${YEAR}/${DAY}.txt"

cat > "${YEAR}/${DAY}.clj" << EOF
(require '[clojure.string :as str])

(def input
  (-> "${DAY}.txt"
      slurp
      str/trim
      str/split-lines))

(defn part-one [stream]

)

(defn part-two [stream]

)

(println (part-one input))
(println (part-two input))

(part-one "")
(part-two "")
EOF
