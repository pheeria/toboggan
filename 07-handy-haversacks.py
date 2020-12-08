import re
from utils import get_text

input = get_text('07')

# input = [
#     "light red bags contain 1 bright white bag, 2 muted yellow bags.",
#     "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
#     "bright white bags contain 1 shiny gold bag.",
#     "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
#     "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
#     "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
#     "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
#     "faded blue bags contain no other bags.",
#     "dotted black bags contain no other bags.",
# ]

# input = [
#     "shiny gold bags contain 2 dark red bags.",
#     "dark red bags contain 2 dark orange bags.",
#     "dark orange bags contain 2 dark yellow bags.",
#     "dark yellow bags contain 2 dark green bags.",
#     "dark green bags contain 2 dark blue bags.",
#     "dark blue bags contain 2 dark violet bags.",
#     "dark violet bags contain no other bags.",
# ]
bag_key = r"^(\w+ \w+)"
bag_value = r"(\d) (\w+ \w+)"
result = {}
for i in input:
    bag = {}
    key = re.search(bag_key, i).group(0)
    value = {}
    for group in re.findall(bag_value, i):
        value[group[1]] = int(group[0])
    result[key] = value

def find_shiny_gold_bags(dic, bag):
    if not dic[bag]:
        return 0
    elif "shiny gold" in dic[bag]:
        return dic[bag]["shiny gold"]
    else:
        golds = 0
        for color in dic[bag].keys():
            golds += find_shiny_gold_bags(dic, color)
        return golds

counter = 0
for bag in result.keys():
    if find_shiny_gold_bags(result, bag):
        counter += 1
print(counter)


def find_bags(dic, bag):
    if not dic[bag]:
        return 0
    elif isinstance(dic[bag], int):
        return dic[bag]
    else:
        result = 0
        for color, amount in dic[bag].items():
            result += amount
            result += find_bags(dic, color) * amount
        return result
ins = find_bags(result, "shiny gold")
print(ins)
