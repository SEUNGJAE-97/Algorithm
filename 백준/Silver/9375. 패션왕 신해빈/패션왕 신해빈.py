T = int(input())

for i in range(T):
    map = {}
    answer = 1
    # 해빈이가 가진 의상 수 : n
    n = int(input())
    for i in range(n):
        a, b = input().split()
        # 만일 map에 존재하지 않는 카테고리라면 1
        if not b in map:
            map[b] = 1
        # 만일 map에 존재하는 카테고리라면 1을 더해준다.
        else:
            map[b] += 1
    # headgear를 입을 경우 hat, turban, x
    # eyewear를 입는 경우 sunglasses, x
    # 모든 경우의 수는 3 * 2 - 1 = 5가된다.

    for k in map.keys():
        # 이때 아무것도 입지 않는다는 선택지를 추가하기 위하여 +1을 더해준다.
        answer *= (map[k]+1)
    # 아무것도 입지 않는 경우를 제외해야하므로 1을 뺴준다.
    print(answer-1)