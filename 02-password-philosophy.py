#!/usr/bin/env python3

from utils import get_text

input = get_text('02')
store = []

for line in input:
    r, t, p = line.split(' ')
    mn, mx = map(int, r.split('-'))
    term = t.rstrip(':')
    store.append({
        'min': mn,
        'max': mx,
        'term': term,
        'password': p
    })

result = 0

for entry in store:
    occurences = entry['password'].count(entry['term'])
    if entry['min'] <= occurences <= entry['max']:
        result += 1

print(result)

result = 0

for entry in store:
    first = entry['password'][entry['min'] - 1] == entry['term']
    second = entry['password'][entry['max'] - 1] == entry['term']
    if first ^ second:
        result += 1

print(result)
