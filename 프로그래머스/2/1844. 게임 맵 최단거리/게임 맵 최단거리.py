from collections import deque
from dataclasses import dataclass
dx = [0,0, 1, -1]
dy = [1,-1, 0, 0]

@dataclass
class Node:
    x: int
    y: int
    cost : int

def bfs(maps, start):
    queue = deque()
    queue.append(start)
    visited = [[False] * len(maps[0]) for _ in range(len(maps))]
    visited[start.x][start.y] = True

    while queue:
        node = queue.popleft()
        # 도착지점에 도달하면 비용 반환
        if node.x == len(maps) - 1 and node.y == len(maps[0]) - 1:
            return node.cost
    
        for i in range(4):
            nx = node.x + dx[i]
            ny = node.y + dy[i]
            if 0 <= nx < len(maps) and 0 <= ny < len(maps[0]) and not visited[nx][ny] and maps[nx][ny] == 1:
                visited[nx][ny] = True
                next_node = Node(nx, ny, node.cost + 1)
                queue.append(next_node)
    return -1 # 도착지점에 도달하지 못한 경우 -1 반환

def solution(maps):
    return bfs(maps, Node(0,0,1))