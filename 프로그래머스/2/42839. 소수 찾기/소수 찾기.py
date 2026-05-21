from itertools import combinations, permutations
import math
def is_prime(num):
    if num < 2 : return False
    if num == 2 : return True
    if num % 2 == 0 : return False
    for i in range(3, int(math.sqrt(num)+1), 2):
        if num % i == 0:
            return False
    return True

def solution(numbers):
    answer = 0
    set_numbers = set()
    for i in range(1, len(numbers)+1):
        for perm in permutations(numbers, i):
            num = int(''.join(perm))
            set_numbers.add(num)

    for number in set_numbers:
        if is_prime(number):
            answer += 1
    return answer
