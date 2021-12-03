from typing import List
import time
import json

def get_json(num: str) -> List[str]:
    result = []
    with open(f'./inputs/{num}.json') as file:
        result = json.load(file)
    return result

def get_text(num: str) -> List[str]:
    result = []
    with open(f'./inputs/{num}.txt') as file:
        result = file.read().splitlines()
    return result

def measure(func):
    def inner():
        splitted = func.__name__.split('_')
        capitalized = map(lambda s: s.capitalize(), splitted)
        joined = ' '.join(capitalized)
        print('>> ' + joined)
        tic = time.perf_counter()
        func()
        toc = time.perf_counter()
        print(f'Finished in {(toc - tic) * 1000:0.2f} ms', end='\n\n')

    return inner

