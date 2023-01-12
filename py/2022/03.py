from utils import get_data

test_data = [
    "vJrwpWtwJgWrhcsFMMfFFhFp",
    "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
    "PmmdzqPrVvPwwTWBwg",
    "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
    "ttgJtRGJQctTZtZT",
    "CrZsJsPPZsGzwwsLwLmpwMDw",
]

data = get_data("03.txt")

def part_one(given):
    result = 0

    for line in given:
        seen = set()
        middle = len(line) // 2
        for char in line[:middle]:
            seen.add(char)
        for char in line[middle:]:
            if char in seen:
                seen.remove(char)
                if char.islower():
                    result += ord(char) - 96
                else:
                    result += ord(char) - 38

    return result


def part_two(given):
    result = 0
    
    for index in range(0, len(given), 3):
        seen = set()
        for char in given[index]:
            seen.add(char)

        second_seen = set()
        for char in given[index + 1]:
            if char in seen:
                second_seen.add(char)

        for char in given[index + 2]:
            if char in second_seen:
                second_seen.remove(char)
                if char.islower():
                    result += ord(char) - 96
                else:
                    result += ord(char) - 38

    return result


print(part_one(test_data))
print(part_one(data))

print(part_two(test_data))
print(part_two(data))
