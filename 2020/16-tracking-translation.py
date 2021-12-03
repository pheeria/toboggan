import re
from pprint import pprint
from utils import get_text

input = get_text('16')

# input = [
#     'class: 1-3 or 5-7',
#     'row: 6-11 or 33-44',
#     'seat: 13-40 or 45-50',
#     '',
#     'your ticket:',
#     '7,1,14',
#     '',
#     'nearby tickets:',
#     '7,3,47',
#     '40,4,50',
#     '55,2,20',
#     '38,6,12'
# ]

# input = [
#     'class: 0-1 or 4-19',
#     'row: 0-5 or 8-19',
#     'seat: 0-13 or 16-19',
#     '',
#     'your ticket:',
#     '11,12,13',
#     '',
#     'nearby tickets:',
#     '3,9,18',
#     '15,1,5',
#     '5,14,9'
# ]

def extract_range(raw):
    l, h = raw.split('-')
    return { 'low': int(l), 'high': int(h) }

fields = {}
for line in input:
    if not line:
        break
    else:
        name = line[:line.index(':')]
        fields[name] = []

        groups = re.findall(r'(\d+-\d+)', line)
        fields[name].append(extract_range(groups[0]))
        fields[name].append(extract_range(groups[1]))

your_index = input.index('your ticket:')
your_ticket = [int(num) for num in input[your_index + 1].split(',')]

nearby_index = input.index('nearby tickets:')
nearby_tickets = []

for i in range(1, len(input) - nearby_index):
    nearby_tickets.append([int(num) for num in input[nearby_index + i].split(',')])

def part_one():
    errors = 0

    for ticket in nearby_tickets:
        for val in ticket:
            found = False
            for field in fields.values():
                if (field[0]['low'] <= val <= field[0]['high']
                        or field[1]['low'] <= val <= field[1]['high']):
                    found = True
                    break
            if not found:
                errors += val
            
    print(errors)

def part_two():
    valid_tickets = []
    for ticket in nearby_tickets:
        is_valid = True
        for val in ticket:
            found = False
            for field in fields.values():
                if (field[0]['low'] <= val <= field[0]['high']
                        or field[1]['low'] <= val <= field[1]['high']):
                    found = True
                    break
            if not found:
                is_valid = False
                break
        if is_valid:
            valid_tickets.append(ticket)

    possibilities = []
    for idx, ticket in enumerate(valid_tickets):
        possibilities.append({})
        for i, val in enumerate(ticket):
            possibilities[idx][i] = []
            for name, field in fields.items():
                if (field[0]['low'] <= val <= field[0]['high']
                        or field[1]['low'] <= val <= field[1]['high']):
                    possibilities[idx][i].append(name)

    your_possibilities = {}
    for i, val in enumerate(your_ticket):
        your_possibilities[i] = []
        for name, field in fields.items():
            if (field[0]['low'] <= val <= field[0]['high']
                    or field[1]['low'] <= val <= field[1]['high']):
                your_possibilities[i].append(name)


    correct_columns = { k:[] for k in range(len(possibilities[0]))}
    for key, values in your_possibilities.items():
        for name in values:
            found = True
            for possibility in possibilities:
                if name not in possibility[key]:
                    found = False
            if found:
                correct_columns[key].append(name)
    named_columns = {}
    
    all_found = False
    while not all_found: 
        for key, values in correct_columns.items():
            if len(values) == 1:
                named_columns[values[0]] = key
        for key in named_columns.keys():
            for values in correct_columns.values():
                if key in values:
                    values.remove(key)
        if sum(len(values) for values in correct_columns.values()) == 0:
            all_found = True
    pprint(named_columns)
    result = 1
    for key, value in named_columns.items():
        if key.startswith('departure'):
            print(key, value)
            result *= your_ticket[value]
    print(result)

part_two()
