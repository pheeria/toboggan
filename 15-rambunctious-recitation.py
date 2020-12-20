

def part_one(src, nth):
    result = list(src)
    memory = { val:idx for idx, val in enumerate(result)}
    for i in range(len(result) - 1, nth - 1):
        previous = result[i]
        if previous not in memory:
            memory[previous] = i
            result.append(0)
        else:
            result.append(i - memory[previous])
            memory[previous] = i
    return result


assert part_one([0, 3, 6], 10) == [0, 3, 6, 0, 3, 3, 1, 0, 4, 0]
assert part_one([1, 3, 2], 2020)[-1] == 1
assert part_one([2, 1, 3], 2020)[-1] == 10
assert part_one([1, 2, 3], 2020)[-1] == 27
assert part_one([2, 3, 1], 2020)[-1] == 78
assert part_one([3, 2, 1], 2020)[-1] == 438
assert part_one([3, 1, 2], 2020)[-1] == 1836

print(part_one([0, 5, 4, 1, 10, 14, 7], 2020)[-1])

assert part_one([0, 3, 6], 30000000)[-1] == 175594
assert part_one([1, 3, 2], 30000000)[-1] == 2578
assert part_one([2, 1, 3], 30000000)[-1] == 3544142
assert part_one([1, 2, 3], 30000000)[-1] == 261214
assert part_one([2, 3, 1], 30000000)[-1] == 6895259
assert part_one([3, 2, 1], 30000000)[-1] == 18
assert part_one([3, 1, 2], 30000000)[-1] == 362

print(part_one([0, 5, 4, 1, 10, 14, 7], 30000000)[-1])
