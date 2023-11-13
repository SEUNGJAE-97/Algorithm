T = int(input())

for i in range(T):
    N = int(input())
    array = []
    cnt = 1
    # 불합격 기준을 무한으로 설정한다.
    standard = float('inf')
    for _ in range(N):
        thing = list(map(int, input().split()))
        array.append(thing)
    # 서류 등수에 따라 정렬한다.
    array.sort(key=lambda x: x[0])

    # 면접 결과가 기준보다 높다면 cnt에 1씩 더한다.
    for k in range(N):
        if standard > array[k][1]:
            standard = array[k][1]
            cnt += 1

    print(cnt-1)