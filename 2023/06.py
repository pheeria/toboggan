
def get_text(num: str) -> list[str]:
    result = []
    with open(f'./inputs/{num}.txt') as file:
        result = file.read().splitlines()
    return result
