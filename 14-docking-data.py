import re
from utils import get_text, measure

input = get_text('14')
# input = [
#     'mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X',
#     'mem[8] = 11',
#     'mem[7] = 101',
#     'mem[8] = 0'
# ]

def process(src, mask):
    result = ''
    binary = str(bin(src)).lstrip('0b').rjust(36, '0')

    for i in range(36):
        if mask[i] == 'X':
            result += binary[i]
        else:
            result += mask[i]

    return int(result, 2)

@measure
def part_one():
    memory = {}

    mask = ''

    for line in input:
        if 'mask' in line:
            mask = line.lstrip('mask = ')
        else:
            matches = re.match(r"mem\[(\d+)\] = (\d+)", line)
            registry = int(matches.group(1))
            src = int(matches.group(2))
            
            processed = process(src, mask)
            memory[registry] = processed

    print(sum(memory.values()))

part_one()
