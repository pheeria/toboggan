def get_data(filename):
    result = []

    with open(filename) as file:
        result = file.read().splitlines()

    return result
