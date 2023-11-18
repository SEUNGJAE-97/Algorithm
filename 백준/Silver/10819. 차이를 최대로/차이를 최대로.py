from itertools import permutations


def circulator(array):
    answer = 0
    for i in range(len(array)-1):
        answer += abs((array[i] - array[i + 1]))
    return answer


N = int(input())
A = list(map(int, input().split()))
# -로 초기화
max_num = float('-inf')

for j in permutations(A):
    max_num = max(max_num, circulator(j))
    
print(max_num)