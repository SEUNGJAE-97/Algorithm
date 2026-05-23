# bridge_length : 다리에 올라갈수잇는 최대 트럭 수
# weight : 다리가 견딜 수 있는 최대 하중 즉 weigth보다 무거운 트럭은 다리에 올라갈 수 없음
# truck_weights : 트럭들의 무게
from collections import deque

def solution(bridge_length, weight, truck_weights):
    bridge = deque([0] * bridge_length)  # 다리 위 트럭의 무게를 저장하는 큐
    trucks = deque(truck_weights)
    sec = 0
    current_weight = 0

    while bridge or current_weight > 0:
        sec += 1
        # 다리에서 트럭이 한 칸 이동
        exited = bridge.popleft()
        current_weight -= exited
        
        # 다리에 트럭이 올라갈 수 있는지 확인
        if trucks :
            if current_weight + trucks[0] <= weight:
                next_truck = trucks.popleft()
                bridge.append(next_truck)
                current_weight += next_truck
            else:
                bridge.append(0)  # 트럭이 올라가지 못하면 0을 추가하여 시간만 흐르게 함
    return sec
