def part_one():
    answer = 0

    with open("01.txt") as file:
        interim = 0
        for line in file.read().splitlines():
            if line:
                interim += int(line)
            else:
                answer = max(interim, answer)
                interim = 0

    answer = max(interim, answer)

    print(answer)

def part_two():
    answer = []

    with open("01.txt") as file:
        interim = 0
        for line in file.read().splitlines():
            if line:
                interim += int(line)
            else:
                answer.append(interim)
                answer.sort(reverse=True)
                answer = answer[:3]
                interim = 0
        answer.append(interim)
        answer.sort(reverse=True)
        answer = answer[:3]
    print(sum(answer))

part_two()
