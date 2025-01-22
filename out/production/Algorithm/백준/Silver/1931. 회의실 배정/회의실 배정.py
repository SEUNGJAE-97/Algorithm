# 회의 수
N = int(input())
# 회의
Meeting = []

for i in range(N):
    A, B = map(int, input().split())
    Meeting.append([A, B])

# 회의 시작 시간이 제일 짧은순으로 정렬한다.
# 만일 종료시간이 같다면 회의 끝나는 시간이 짧은것이 앞으로 오도록 한다.
Meeting.sort(key=lambda x: (x[1], x[0]))

answer = 0
endTime = 0

for i in range(len(Meeting)):
    # 시작 시간이 endtime 보다 큰지 확인한다.
    # 앞선 회의가 4시에 종료되었는데, 다음 회의를 3시에 시작할 수 없으므로
    if endTime <= Meeting[i][0]:
        # 종료 시간을 갱신하고, answer에 1을 더한다.
        endTime = Meeting[i][1]
        answer += 1

print(answer)