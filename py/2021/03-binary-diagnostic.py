#!/usr/bin/env python3

from utils import get_text

def bin_to_dec(binary):
    result = 0
    width = len(binary)
    for i in range(width):
        result += 2 ** i * int(binary[width - i - 1])
    return result

def part_one(binaries):
    width = len(binaries[0]) 
    digits = {i: {0: 0, 1: 0} for i in range(width)}
    for binary in binaries:
        for i, digit in enumerate(binary):
            bit = int(digit)
            digits[i][bit] += 1
    
    gamma = []
    epsilon = []
    for i in range(width):
        if digits[i][0] > digits[i][1]:
            gamma.append(0)
            epsilon.append(1)
        else:
            gamma.append(1)
            epsilon.append(0)

    gamma_dec = bin_to_dec(gamma)
    epsilon_dec = bin_to_dec(epsilon)

    return gamma_dec * epsilon_dec


def part_two(binaries):
    def calculate(numbers, predicate):
        width = len(numbers[0]) 
        result = ""
        bnrs = numbers
        for index in range(width):
            if len(bnrs) == 1:
                result = bnrs[0]
                break
            digits = {"0": 0, "1": 0}
            for binary in bnrs:
                digits[binary[index]] += 1

            if predicate(digits["0"], digits["1"]):
                result += "1"
            else:
                result += "0"
            
            filtered = []
            for binary in bnrs:
                if binary.startswith(result):
                    filtered.append(binary)
            bnrs = filtered
        return result

    o2 = calculate(binaries, lambda x, y: x <= y)
    co2 = calculate(binaries, lambda x, y: y < x)

    o2_dec = bin_to_dec(o2)
    co2_dec = bin_to_dec(co2)
    
    return o2_dec * co2_dec

data = [
    "00100",
    "11110",
    "10110",
    "10111",
    "10101",
    "01111",
    "00111",
    "11100",
    "10000",
    "11001",
    "00010",
    "01010",
]
data = get_text('2103')

print(part_one(data))
print(part_two(data))
