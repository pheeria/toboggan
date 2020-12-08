#!/usr/bin/env python3

from utils import get_json

input = get_json('05')
store = []
for line in input:
    r_min = 0
    r_max = 127

    for letter in line[:6]:
        if letter == 'F':
            r_max = (r_max + r_min) // 2
        if letter == 'B':
            r_min = (r_max + r_min) // 2 + 1

    row = r_min if line[6] == 'F' else r_max

    c_min = 0
    c_max = 7

    for letter in line[-3:-1]:
        if letter == 'L':
            c_max = (c_max + c_min) // 2
        if letter == 'R':
            c_min = (c_max + c_min) // 2 + 1

    column = c_min if line[-1] == 'L' else c_max
    seat_id = row * 8 + column
    store.append({ "row": row, "column": column, "seat_id": seat_id })

print(max(s['seat_id'] for s in store))

for r in range(10, 120):
    for c in range(8):
        tmp = {
            'row': r,
            'column': c,
            'seat_id': r * 8 + c
        }
        if tmp not in store:
            print(tmp)

allocated = [s['seat_id'] for s in store]
mn = min(allocated)
mx = max(allocated)

for i in range(mn, mx - mn):
    if i not in allocated:
        print(i)
