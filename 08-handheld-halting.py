#!/usr/bin/env python3

from utils import get_json
from copy import deepcopy

input = get_json('08')
# input = [
#     "nop +0",
#     "acc +1",
#     "jmp +4",
#     "acc +3",
#     "jmp -3",
#     "acc -99",
#     "acc +1",
#     "jmp -4",
#     "acc +6",
# ]

instructions = []

for line in input:
    op, num = line.split(' ')
    instructions.append({ 'op': op, 'num': int(num) })

accumulator = 0
index = 0
visited = []

while index not in visited:
    visited.append(index)

    instruction = instructions[index]
    op = instruction['op']
    num = instruction['num']
    if op == 'nop':
        index += 1
    elif op == 'acc':
        index += 1
        accumulator += num
    else:
        index += num

print(accumulator)
    

last_change = 0
found = False
instructions_length = len(instructions)

while not found:
    accumulator = 0
    index = 0
    visited = []

    altered = deepcopy(instructions)
    for i in range(last_change, instructions_length):
        if altered[i]['op'] == 'nop':
            altered[i]['op'] = 'jmp'
            break
        elif altered[i]['op'] == 'jmp':
            altered[i]['op'] = 'nop'
            break


    while not found:
        visited.append(index)
        instruction = altered[index]
        op = instruction['op']
        num = instruction['num']
        if op == 'nop':
            index += 1
        elif op == 'acc':
            index += 1
            accumulator += num
        else:
            index += num
        
        if index in visited:
            last_change += 1
            break
        if index == instructions_length:
            print(accumulator)
            found = True
            break

