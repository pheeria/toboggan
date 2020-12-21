#!/usr/bin/env python3

from utils import get_json, measure


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

adapters = sorted(input)
adapters = [0, *adapters, max(adapters) + 3]

@measure
def part_one():
    diffs = { 1: 0, 2: 0, 3: 0}
    for i in range(1, len(adapters)):
        diffs[adapters[i] - adapters[i - 1]] += 1

    print(diffs[1] * diffs[3])

memo = {}
def recursive_count(i):
    if i == len(adapters) - 1:
        return 1
    if i in memo:
        return memo[i]
    result = 0
    end = min(i + 4, len(adapters))
    for j in range(i + 1, end):
        try:
            if adapters[j] - adapters[i] <= 3:
                result += recursive_count(j)
        except:
            print(i, j)
    memo[i] = result
    return result

print(recursive_count(0))

