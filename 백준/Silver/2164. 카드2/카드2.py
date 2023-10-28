"""
[1, 2, 3, 4, 5, 6]
일때 1을 버리고, 2를 제일 아래로 보낸다.
>[3,4,5,6,2]
>[5,6,2,4]
>[2,4,6]
>[6,4]
>[4]가 답이된다.
"""
import sys
from collections import deque

N = int(sys.stdin.readline())

queue = deque()
for i in range(N):
    # N = 6일때, queue = [1,2,3,4,5,6]으로 큐를 생성한다.
    queue.append(i+1)

# 큐의 길이가 1보다 크다면, 즉 마지막 하나가 될때까지 반복할 것이므로
while len(queue) > 1:
    # 제일 앞에 존재하는 1을 제거한다.
    queue.popleft()
    # 1이 제거되었고, 그다음으로 존재하는 2를 제거함과 동시에
    # 큐에 추가하여 제일 뒤로 보낸다.
    queue.append(queue.popleft())

print(queue.pop())