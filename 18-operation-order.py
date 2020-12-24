import re
from utils import get_text

input = get_text('18')
# input = [
#     '1 + 2 * 3 + 4 * 5 + 6',
#     '1 + (2 * 3) + (4 * (5 + 6))',
#     '2 * 3 + (4 * 5)',
#     '5 + (8 * 3 + 9 + 3 * 4 * 3)',
#     '5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))',
#     '((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2',
#     # '6 + 7 * 16 + 7',
#     # '1 + 1 + 1 + (5 + 5)',
#     # '3 * 3 + (1 * 1 * 1)',
#     # '2 + 3 * 4 * 2 + 3 * 7',
#     # '1 + 2 + 3 + 4',
#     # '(1 + 2 + 3 + 4)',
#     # '(6 + 9 * 9 + 7 * (5 * 7 * 6 + 9 * 5 + 2) + 3) * (2 + 4 * (8 * 3 + 2 * 4 * 2 * 8) + 9) * (6 + 6 * 2 * 6) + 5'
#     '7 + 7 + 7 + (4 + 7)',
#     # '8 * 2 * (2 + 5 + (5 * 4 * 2 + 7 + 3 * 4)) + ((4 + 9) + (4 * 8) + (6 * 8 + 6) + (4 * 3 * 9) + 5) * 7'
# ]

def left_to_right(seq, index):
    result = { "index": index, "value": 0}
    op = '+'
    while result["index"] < len(seq):
        char = seq[result["index"]]
        if char == ' ':
            result["index"] += 1
            continue
        elif char in ['+', '*']:
            op = char
            result["index"] += 1
        elif char == '(':
            tmp = left_to_right(seq, result["index"] + 1)
            result["value"] = eval(f"{result['value']} {op} {tmp['value']}")
            result["index"] = tmp["index"]
        elif char == ')':
            result["index"] += 1
            return result
        else:
            result["value"] = eval(f"{result['value']} {op} {char}")
            result["index"] += 1

    return result

    
def part_one():
    results = []
    for each in input:
        result = left_to_right(each, 0)
        results.append(result["value"])

    return sum(results)

def repl(match):
    return str(eval(match.group(0)))

def part_two():
    results = []
    addition = r"\d+ \+ \d+"
    parentheses = r"\([^\(\)]+\)"
    for line in input:
        tmp = line
        while len(re.findall(addition, tmp)):
            tmp = re.sub(addition, repl, tmp)

        while len(re.findall(parentheses, tmp)):
            tmp = re.sub(parentheses, repl, tmp)

            while len(re.findall(addition, tmp)):
                tmp = re.sub(addition, repl, tmp)

        tmp = eval(tmp)
        results.append(tmp)
    return sum(results)

print(part_two())
