#!/usr/bin/env python3

from utils import get_json


input = get_json('10')

# input = [
#     16,
#     10,
#     15,
#     5,
#     1,
#     11,
#     7,
#     19,
#     6,
#     12,
#     4,
# ]
diffs = { 1: 1, 2: 0, 3: 1}
adapters = sorted(input)
print(adapters)

for i in range(1, len(adapters)):
    diffs[adapters[i] - adapters[i - 1]] += 1

print(diffs)
print(diffs[1] * diffs[3])
