from enum import IntEnum
from utils import get_text

class Direction(IntEnum):
    E = 0
    S = 90
    W = 180
    N = 270

input = get_text('12')
# input = [
#     "F10",
#     "N3",
#     "F7",
#     "R90",
#     "F11",
# ]

def part_one():
    direction = Direction.E
    position = [0, 0]

    for instruction in input:
        ndir = instruction[0]
        npos = int(instruction[1:])

        if ndir == 'N' or ndir == 'F' and direction == direction.N:
            position[1] += npos
        elif ndir == 'S' or ndir == 'F' and direction == direction.S:
            position[1] -= npos
        elif ndir == 'E' or ndir == 'F' and direction == direction.E:
            position[0] += npos
        elif ndir == 'W' or ndir == 'F' and direction == direction.W:
            position[0] -= npos
        elif ndir == 'L':
            direction = Direction((direction - npos + 360) % 360)
        elif ndir == 'R':
            direction = Direction((direction + npos) % 360)

        print(position)

    print(abs(position[0]) + abs(position[1]))


def part_two():
    position = [0, 0]
    waypoint = [10, 1]

    for instruction in input:
        ndir = instruction[0]
        npos = int(instruction[1:])

        if ndir == 'N':
            waypoint[1] += npos
        elif ndir == 'S':
            waypoint[1] -= npos
        elif ndir == 'E':
            waypoint[0] += npos
        elif ndir == 'W':
            waypoint[0] -= npos
        elif ndir in ['L', 'R']:
            if npos == 180:
                waypoint[0] = -waypoint[0]
                waypoint[1] = -waypoint[1]
            elif ndir == 'L' and npos == 90 or ndir == 'R' and npos == 270:
                waypoint[0], waypoint[1] = -waypoint[1], waypoint[0]
            elif ndir == 'R' and npos == 90 or ndir == 'L' and npos == 270:
                waypoint[0], waypoint[1] = waypoint[1], -waypoint[0]
        else:
            position[0] += npos * waypoint[0] 
            position[1] += npos * waypoint[1] 

    print(abs(position[0]) + abs(position[1]))

part_two() 
