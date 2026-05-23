from collections import deque

def solution(s):
    answer = True
    queue = deque()
    for char in s:
        if char == '(':
            queue.append(char)
        elif char == ')':
            if not queue:
                return False
            queue.pop()
    
    if queue:
        return False
    return True