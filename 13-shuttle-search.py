#!/usr/bin/env python3

from utils import get_text

input = get_text('13')
# input = [
#     '939',
#     # '7,13,x,x,59,x,31,19'
#     # '17,x,13,19'
#     # '67,7,x,59,61'
#     '1789,37,47,1889'
# ]

time = int(input[0])
original = input[1].split(',')
buses = list(map(lambda x: int(x), filter(lambda x: x != 'x', original)))
arrivals = []
for bus in buses:
    prev = time // bus
    arrivals.append({
        'id': bus,
        'arrival': bus * (prev + 1)
    })

earliest = next(filter(lambda x: x['arrival'] > time, sorted(arrivals, key=lambda k: k['arrival'])))
print(earliest['id'] * (earliest['arrival'] - time))

def closure(idx, num, sign):
    def f(x):
        print(f"x = {x} idx = {idx * sign} num = {num}")
        return (x + (idx * sign)) % num == 0
    return f

checks = []
largest = max(buses)
index = original.index(str(largest))

for i in range(len(original)):
    if i == index or original[i] == 'x':
        pass
    else:
        s = -1 if i < index else 1
        n = int(original[i])
        checks.append({
            'num': n,
            'func': closure(abs(index - i), n, s)
        })

found = False
final = 100000000000265
funcs = list(map(lambda x: x['func'], sorted(checks, key = lambda k: k['num'], reverse = True)))

while not found:
    final += largest
    found = all(map(lambda x: x(final), funcs))
    
print(final - index)
