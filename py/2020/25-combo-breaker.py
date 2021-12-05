input = [
    1965712,
    19072108,
    # 5764801,
    # 17807724,
]

subject_number = 7
divide_by = 20201227

card_pub = input[0]
door_pub = input[1]


def get_loop_size(public_key):
    result = 0
    value = 1
    while not value == public_key:
        value *= subject_number
        value %= divide_by
        result += 1

    return result

print(get_loop_size(card_pub))
print(get_loop_size(door_pub))

def decrypt(size, pub):
    result = 1
    for i in range(size):
        result *= pub
        result %= divide_by
    return result

print(decrypt(get_loop_size(card_pub), door_pub))
print(decrypt(get_loop_size(door_pub), card_pub))
