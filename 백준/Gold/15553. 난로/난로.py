def main():
    # 친구 수 N, 성냥 개수 K
    N, K = map(int, input().split())
    arr = [int(input()) for _ in range(N)]
    interval = []
    start_t = -1
    K -= 1

    for i in range(N):
        # 첫번째 친구가 오는 경우
        if start_t == -1:
            start_t = arr[i]
            continue
        # 현재 친구와 이전 친구 방문시간 간격을 interval에 추가
        interval.append(arr[i] - start_t)
        # 시작 시간을 현재 친구 방문 시간으로 업데이트
        start_t = arr[i]

    interval.sort()
    answer = 1

    for j in range(len(interval)):
        if K:
            # 성냥이 남아 있는 경우
            answer += 1
            # 인터벌이 가장 큰 경우 제거
            interval.pop()
            K -= 1
        else:
            # 성냥이 없는 경우엔  간격을 더해서 켜져 있는 시간을 늘린다.
            answer += interval.pop()

    print(answer)


if __name__ == '__main__':
    main()