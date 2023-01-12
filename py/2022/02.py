from utils import get_data

def part_one():
    answer = 0

    game = {
        "A": { "X": 3, "Y": 6, "Z": 0 },
        "B": { "X": 0, "Y": 3, "Z": 6 },
        "C": { "X": 6, "Y": 0, "Z": 3 },
        "X": 1,
        "Y": 2,
        "Z": 3
    }
    data = get_data("02.txt")
    for line in data:
        first, second = line.split(" ")
        answer += game[second]
        answer += game[first][second]

    print(answer)

def part_two():
    result = 0

    game = {
        "A": { "X": 3, "Y": 1, "Z": 2 },
        "B": { "X": 1, "Y": 2, "Z": 3 },
        "C": { "X": 2, "Y": 3, "Z": 1 },
        "X": 0,
        "Y": 3,
        "Z": 6
    }
    data = get_data("02.txt")
    for line in data:
        first, second = line.split(" ")
        result += game[second]
        result += game[first][second]

    print(result)

part_two()


