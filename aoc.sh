#! /usr/bin/env bash

if [ -z "$1" ]; then
    echo "Day missing!"
    exit 1
fi

DAY="${1}"
curl --silent --location "https://adventofcode.com/2020/day/${DAY}/input" \
    --cookie "session=${AOC_TOKEN}" --output "inputs/${DAY}.txt"
