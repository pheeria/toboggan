#!/usr/bin/env python3

from utils import get_text
import re

input = get_text('04')

required = ['byr', 'iyr', 'eyr', 'hgt', 'hcl', 'ecl', 'pid']
results = []
passport = {}
for line in input:
    if not line:
        results.append(passport)
        passport = {}
    else:
        splitted = line.split(' ')
        for props in splitted:
            key, value = props.split(':')
            passport[key] = value
counter = 0
for p in results:
    counter += 1
    for r in required:
        if r not in p:
            counter -= 1
            break
print(counter)

counter = 0
for p in results:
    if 'byr' not in p or not 1920 <= int(p['byr']) <= 2002:
        # print('byr', p)
        continue
    if 'iyr' not in p or not 2010 <= int(p['iyr']) <= 2020:
        # print('iyr', p)
        continue
    if 'eyr' not in p or not 2020 <= int(p['eyr']) <= 2030:
        # print('eyr', p)
        continue
    if 'ecl' not in p or p['ecl'] not in ['amb', 'blu', 'brn', 'gry', 'grn', 'hzl', 'oth']:
        # print('ecl', p)
        continue
    if 'pid' not in p or re.search('^[0-9]{9}$', p['pid']) is None:
        # print('pid', p)
        continue
    if 'hcl' not in p or re.search('^#[0-9|a-f]{6}$', p['hcl']) is None:
        # print('hcl', p)
        continue
    if ('hgt' not in p
        or ('cm' not in p['hgt'] and 'in' not in p['hgt'])
        or ('cm' in p['hgt'] and not 150 <= int(p['hgt'].rstrip('cm')) <= 193)
        or ('in' in p['hgt'] and not 59 <= int(p['hgt'].rstrip('in')) <= 76)):
        # print('hgt', p)
        continue
    counter += 1

print(counter)


