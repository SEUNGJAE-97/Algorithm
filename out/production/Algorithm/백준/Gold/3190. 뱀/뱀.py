"""
보드의 크기(N) : 6
사과의 개수(K) : 3
사과의 위치 :
3 4
2 5
5 3
뱀의 방향 변환 횟수(L) :  3
변환 정보(정수 X, 문자 C) :
3 D = 3초 후 오른쪽으로
15 L = 15초 후 왼쪽으로
17 D = 17초 후 오른쪽으로

출력 : 뱀의 머리가 벽을 부딪히거나 자신의 몸에 닿을때

"""

from collections import deque


def direction_change(d, c):
    if c == 'L':
        d = (d - 1) % 4
    else:
        d = (d + 1) % 4
    return d


N = int(input())
K = int(input())

matrix = [[0] * N for _ in range(N)]

# 사과의 개수만큼 반복하여 2차원 배열에 사과가 있는
# 부분은 1로 표시한다.
for _ in range(K):
    x, y = map(int, input().split())
    matrix[x - 1][y - 1] = 1

# 방향 전환 횟수를 입력 받는다.
L = int(input())
# 딕셔너리 times
times = {}
for i in range(L):
    X, C = input().split()
    times[int(X)] = C

dy = [-1, 0, 1, 0]
dx = [0, 1, 0, -1]

# 초기 위치는 1, 시간 : 1초
direction = 1
time = 1
x, y = 0, 0
# 출발은 0,0 위치에서 시작한다.
snake = deque([[y, x]])

matrix[y][x] = 2

while True:
    y, x = y + dy[direction], x + dx[direction]
    if 0 <= y < N and 0 <= x < N and matrix[y][x] != 2:
        if not matrix[y][x] == 1:
            delY, delX = snake.popleft()
            matrix[delY][delX] = 0
        matrix[y][x] = 2
        snake.append([y,x])
        if time in times.keys():
            direction = direction_change(direction, times[time])
        time +=1
        #print(snake)
    else:
        break

print(time)