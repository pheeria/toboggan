#!/usr/bin/env python3

from utils import get_text
from pprint import pprint

input = get_text('11')

# input = [
#   "L.LL.LL.LL",
#   "LLLLLLL.LL",
#   "L.L.L..L..",
#   "LLLL.LL.LL",
#   "L.LL.LL.LL",
#   "L.LLLLL.LL",
#   "..L.L.....",
#   "LLLLLLLLLL",
#   "L.LLLLLL.L",
#   "L.LLLLL.LL",
# ]

def count_occurences(src, y, x, n):
    result = 0
    height = len(src)
    width = len(src[0])
    
    miny = y - 1 if y - 1 >= 0 else 0
    maxy = y + 1 if y + 1 < height else height - 1

    minx = x - 1 if x - 1 >= 0 else 0 
    maxx = x + 1 if x + 1 < width else width - 1

    for i in range(miny, maxy + 1):
        for j in range(minx, maxx + 1):
            if i == y and x == j:
                continue
            elif src[i][j] == n:
                result += 1
    return result

def count_occurences2(src, y, x, n):
    result = 0
    height = len(src)
    width = len(src[0])
    
    for i in range(y - 1, -1, -1):
        cell = src[i][x]
        if cell == '.':
            pass
        else:
            result = result + 1 if cell == n else result
            break

    for i in range(y + 1, height, 1):
        cell = src[i][x]
        if cell == '.':
            pass
        else:
            result = result + 1 if cell == n else result
            break

    for i in range(x - 1, -1, -1):
        cell = src[y][i]
        if cell == '.':
            pass
        else:
            result = result + 1 if cell == n else result
            break
            
    for i in range(x + 1, width, 1):
        cell = src[y][i]
        if cell == '.':
            pass
        else:
            result = result + 1 if cell == n else result
            break

    topleft = min(y, x)
    for i in range(1, topleft + 1):
        cell = src[y - i][x - i]
        if cell == '.':
            pass
        else:
            result = result + 1 if cell == n else result
            break

    topright = min(y, width - x)
    for i in range(1, topright + 1):
        if y - i < 0 or x + i == width:
            break
        cell = src[y - i][x + i]
        if cell == '.':
            pass
        else:
            result = result + 1 if cell == n else result
            break

    bottomleft = min(height - y, x)
    for i in range(1, bottomleft + 1):
        if y + i == height or x - i < 0:
            break
        cell = src[y + i][x - i]
        if cell == '.':
            pass
        else:
            result = result + 1 if cell == n else result
            break

    bottomright = min(height - y, width - x)
    for i in range(1, bottomright + 1):
        if y + i == height or x + i == width:
            break
        cell = src[y + i][x + i]
        if cell == '.':
            pass
        else:
            result = result + 1 if cell == n else result
            break
    return result

def process(src, tolerance):
    result = []
    height = len(src)
    width = len(src[0])

    for y in range(height):
        result.append("")
        for x in range(width):
            cell = src[y][x]
            if cell == 'L' and count_occurences2(src, y, x, '#') == 0:
                result[y] += '#'
            elif cell == '#' and count_occurences2(src, y, x, '#') >= tolerance:
                result[y] += 'L'
            else:
                result[y] += cell     
    return result

last = process(input, 5)
while True:
    temp = process(last, 5)
    if temp == last:
        break
    else:
        last = temp

seats = 0
for line in last:
    for letter in line:
        if letter == '#':
            seats += 1
print(seats)


# placements = [
# '.......#.',
# '...#.....',
# '.#.......',
# '.........',
# '..#L....#',
# '....#....',
# '.........',
# '#........',
# '...#.....',
# '.##.##.',
# '#.#.#.#',
# '##...##',
# '...L...',
# '##...##',
# '#.#.#.#',
# '.##.##.',
    # '.............',
    # '.L.L.#.#.#.#.',
    # '.............',
# ]

# print(countOccurences2(placements, 4, 3, '#'))
