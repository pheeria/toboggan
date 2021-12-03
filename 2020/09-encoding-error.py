#!/usr/bin/env python3

from utils import get_json

input = get_json('09')

# input = [
#     35,
#     20,
#     15,
#     25,
#     47,
#     40,
#     62,
#     55,
#     65,
#     95,
#     102,
#     117,
#     150,
#     182,
#     127,
#     219,
#     299,
#     277,
#     309,
#     576
# ]

length = len(input)
preamble = 25
def solve():
    for i in range(preamble, length):
        num = input[i]
        found = False
        for j in range(i - preamble, i):
            for k in range(i - preamble, i):
                if input[j] + input[k] == num:
                    found = True
                    break

        if not found:
            print(i, num)
            
            for m in range(i):
                index = m + 1
                tmp = input[m]
                while True:
                    tmp += input[index]
                    if tmp == num:
                        print('Found ', min(input[m:index]) + max(input[m:index]))
                        return
                    if tmp > num:
                        break
                    index += 1
            break

solve()
