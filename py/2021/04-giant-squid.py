#!/usr/bin/env python3

from utils import get_text

def parse_bingo(lines):
    result = {}

    draws = []
    for number in lines[0].split(','):
        draws.append(int(number))

    result["draws"] = draws

    boards = []
    board = []
    values = []
    for line in lines[2:]:
        if line:
            for number in line.split():
                values.append({ "value": int(number), "is_marked": False })
            board.append(values)
            values = []
        else:
            boards.append(board)
            board = []

    result["boards"] = boards
            

    return result

def part_one(bingo):
    found_board = None
    winning_draw = 0
    for draw in bingo["draws"]:
        # mark found numbers
        for board in bingo["boards"]:
            for row in board:
                for number in row:
                    if draw == number["value"]:
                        number["is_marked"] = True

        for board in bingo["boards"]:
            # check board rows
            for row in board:
                is_row_marked = True
                for number in row:
                    if not number["is_marked"]:
                        is_row_marked = False
                        break
                if is_row_marked:
                    found_board = board
                    break
            
            if found_board:
                break

            # check board columns
            for x in range(len(board[0])):
                is_column_marked = True
                for y in range(len(board)):
                    if not board[y][x]["is_marked"]:
                        is_column_marked = False
                        break

                if is_column_marked:
                    found_board = board
                    break

            if found_board:
                break

        if found_board:
            winning_draw = draw
            break

    board_sum = 0
    for row in found_board:
        for number in row:
            if not number["is_marked"]:
                board_sum += number["value"]
    print(winning_draw, board_sum)
    return winning_draw * board_sum

data = """7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

22 13 17 11  0
 8  2 23  4 24
21  9 14 16  7
 6 10  3 18  5
 1 12 20 15 19

 3 15  0  2 22
 9 18 13 17  5
19  8  7 25 23
20 11 10 24  4
14 21 16 12  6

14 21 17 24  4
10 16 15  9 19
18  8 23 26 20
22 11 13  6  5
 2  0 12  3  7

14 21 17 24  4
10 16 15  9  9
18  8 23 26 11
22 11 13  6  5
 2  0 12  3  7
""".split('\n')


data = get_text('2104')
print(part_one(parse_bingo(data)))
# print(part_two(data))
