#!/usr/bin/env python3

# -- Part One --
# Before you leave, the Elves in accounting just need you to fix your expense
# report (your puzzle input) apparently, something isn't quite adding up.
# Specifically, they need you to find the two entries that sum to 2020
# and then multiply those two numbers together.

# -- Part Two --
# The Elves in accounting are thankful for your help; one of them even offers
# you a starfish coin they had left over from a past vacation.
# They offer you a second one if you can find three numbers in your expense
# report that meet the same criteria.

from ..utils import get_json, measure

data = get_json('01')
length = len(data)

@measure
def part_one_unsorted():
    for i in range(0, length - 1):
        for j in range(i + 1, length):
            if data[i] + data[j] == 2020:
                print(data[i] * data[j])

@measure
def part_two_unsorted():
    for i in range(0, length - 2):
        for j in range(i + 1, length - 1):
            for k in range(j + 1, length):
                if data[i] + data[j] + data[k] == 2020:
                    print(data[i] * data[j] * data[k])

@measure
def part_one_sorted():
    sorted_input = sorted(data)
    for i in range(0, length - 1):
        for j in range(i + 1, length):
            if sorted_input[i] + sorted_input[j] == 2020:
                print(sorted_input[i] * sorted_input[j])

@measure
def part_two_sorted():
    sorted_input = sorted(data)
    for i in range(0, length - 2):
        for j in range(i + 1, length - 1):
            for k in range(j + 1, length):
                if sorted_input[i] + sorted_input[j] + sorted_input[k] == 2020:
                    print(sorted_input[i] * sorted_input[j] * sorted_input[k])

@measure
def part_one_single_loop():
    result = 0
    store = {}
    for i in range(0, length):
        if data[i] in store:
            result = store[data[i]]
            break
        else:
            store[2020 - data[i]] = (2020 - data[i]) * data[i]
    print(result)


part_one_unsorted()
part_two_unsorted()
part_one_sorted()
part_two_sorted()
part_one_single_loop()
