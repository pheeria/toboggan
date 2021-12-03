#!/usr/bin/env python3

from utils import get_text


def part_one(commands):
    position = [0, 0]
    for command in commands:
        direction, distance = command.split(' ')
        if direction == 'forward':
            position[0] += int(distance)
        elif direction == 'up':
            position[1] -= int(distance)
        else:
            position[1] += int(distance)

    return position[0] * position[1]

def part_two(commands):
    horizontal, vertical, aim = [0, 0, 0]
    for command in commands:
        direction, units = command.split(' ')
        if direction == 'forward':
            horizontal += int(units)
            vertical += (aim * int(units))
        elif direction == 'up':
            aim -= int(units)
        else:
            aim += int(units)

    return horizontal * vertical

data = get_text('02')
print(part_one(data))
print(part_two(data))

