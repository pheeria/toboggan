import re
from itertools import combinations
from utils import get_text, measure

input = get_text('14')
# input = [
#     'mask = 000000000000000000000000000000X1001X',
#     'mem[42] = 100',
#     'mask = 00000000000000000000000000000000X0XX',
#     'mem[26] = 1',
# ]

def process(src, mask):
    result = ''
    binary = str(bin(src)).lstrip('0b').rjust(36, '0')

    for i in range(36):
        if mask[i] == 'X':
            result += binary[i]
        else:
            result += mask[i]

    return int(result, 2)

@measure
def part_one():
    memory = {}
    mask = ''

    for line in input:
        if 'mask' in line:
            mask = line.lstrip('mask = ')
        else:
            matches = re.match(r"mem\[(\d+)\] = (\d+)", line)
            registry = int(matches.group(1))
            src = int(matches.group(2))
            
            processed = process(src, mask)
            memory[registry] = processed

    return sum(memory.values())

def floating(registry, mask):
    result = []
    binary = str(bin(registry)).lstrip('0b').rjust(36, '0')
    final_mask = ''
    powers = []
    for i in range(36):
        if mask[i] == '1':
            final_mask += '1'
        elif mask[i] == '0':
            final_mask += binary[i]
        else:
            final_mask += '0'
            powers.append(2 ** (35 - i))
    base = int(final_mask, 2)
    result.append(base)
    result.append(base + sum(powers))
    for i in range(1, len(powers)):
        combi = combinations(powers, i)
        for each in combi:
            result.append(base + sum(each))
    return result

def part_two():
    memory = {}
    mask = ''

    for line in input:
        if 'mask' in line:
            mask = line.lstrip('mask = ')
        else:
            matches = re.match(r"mem\[(\d+)\] = (\d+)", line)
            registry = int(matches.group(1))
            src = int(matches.group(2))

            for each in floating(registry, mask):
                memory[each] = src

    return sum(memory.values())

print(part_two())
