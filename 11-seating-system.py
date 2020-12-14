#!/usr/bin/env python3

from utils import get_text
from pprint import pprint

input = get_text('11')

input = [
  "L.LL.LL.LL",
  "LLLLLLL.LL",
  "L.L.L..L..",
  "LLLL.LL.LL",
  "L.LL.LL.LL",
  "L.LLLLL.LL",
  "..L.L.....",
  "LLLLLLLLLL",
  "L.LLLLLL.L",
  "L.LLLLL.LL",
]

def countOccurences(src, y, x, n):
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

def countOccurences2(src, y, x, n):
    result = 0
    height = len(src)
    width = len(src[0])
    
    for i in range(y, 0, -1):
        cell = src[i][x]
        if cell == '.':
            pass
        else:
            result = result + 1 if cell == n else result
            break

    for i in range(y, height, 1):
        cell = src[i][x]
        if cell == '.':
            pass
        else:
            result = result + 1 if cell == n else result
            break

    for i in range(x, 0, -1):
        cell = src[y][i]
        if cell == '.':
            pass
        else:
            result = result + 1 if cell == n else result
            break
            
    for i in range(x, width, 1):
        cell = src[y][i]
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
            if cell == 'L' and countOccurences2(src, y, x, '#') == 0:
                result[y] += '#'
            elif cell == '#' and countOccurences2(src, y, x, '#') >= tolerance:
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
# sudoku = [
#     "111",
#     "111",
#     "111",
#     "111",
# ]
# pprint(countOccurences(sudoku, 3, 2, "1"))
