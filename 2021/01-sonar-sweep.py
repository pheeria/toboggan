#!/usr/bin/env python3

from utils import get_json


def part_one(numbers):
    result = 0
    for i in range(1, len(numbers)):
        if numbers[i] > numbers[i - 1]:
            result += 1
    return result

def part_two(numbers):
    result = 0
    for i in range(len(numbers) - 3):
        if numbers[i + 3] > numbers[i]:
            result += 1
    return result

data = get_json('2101')
print(part_one(data))
print(part_two(data))
