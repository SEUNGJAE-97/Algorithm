N, K = map(int, input().split())
won = []

# 동전 리스트를 입력받고, 내림차순으로 정렬한다.
for _ in range(N):
    won.append(int(input()))
won.sort(reverse=True)

cnt = 0
# 동전의 값이 주어진 값보다 크지 않아야함
for i in range(N):
    if K >= won[i]:
        # 몇번 나눌 수 있는지 확인한다.
        cnt += K//won[i]
        # 나누고 남은 돈을 갱신한다.
        K -= won[i] * (K//won[i])
    #print(K)
print(cnt)