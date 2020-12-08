from utils import get_text

input = get_text('06') 

results = []
quiz = {}
for line in input:
    if not line:
        results.append(quiz)
        quiz = {}
    else:
        for letter in line:
            if letter in quiz:
                quiz[letter] += 1
            else:
                quiz[letter] = 1
results.append(quiz)
print(sum(len(quiz.keys()) for quiz in results))


results = []
quiz = {}
maxes = []
mx = 0
for line in input:
    if not line:
        results.append(quiz)
        maxes.append(mx)
        quiz = {}
        mx = 0
    else:
        mx += 1
        for letter in line:
            if letter in quiz:
                quiz[letter] += 1
            else:
                quiz[letter] = 1
results.append(quiz)
maxes.append(mx)
result = 0
for i in range(0, len(maxes)):
    for k, v in results[i].items():
        if v == maxes[i]:
            result += 1

print(result)
