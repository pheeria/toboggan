#!/usr/bin/env python3

from utils import get_json
from math import prod

input = get_json('03')

# Part One
position = 0
threshold = len(input[0])
result = 0

for row in input:
    if row[position] == '#':
        result += 1
    position = (position + 3) % threshold

print(result)

# Part Two
results = []
threshold = len(input[0])
slopes = [{
    'right': 1,
    'down': 1
},{
    'right': 3,
    'down': 1
},{
    'right': 5,
    'down': 1
},{
    'right': 7,
    'down': 1
},{
    'right': 1,
    'down': 2
}]

for slope in slopes:
    result = 0
    position = 0
    print(slope)
    for row in range(0, len(input), slope['down']):
        if input[row][position] == '#':
            result += 1
        position = (position + slope['right']) % threshold
    results.append(result)

print(results)
print(prod(results))
