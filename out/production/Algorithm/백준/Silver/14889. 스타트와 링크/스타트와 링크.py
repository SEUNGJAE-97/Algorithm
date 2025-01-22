"""
N명(짝수)을 N/2로 나누어 팀을 이룬다.
SxS 크기의 2차원 배열에 선수를 배치하여 각 팀의 능력 차가 최소환이 되도록
배치했을때 두 팀 간의 능력차이의 최솟값을 출력한다.

"""

from itertools import combinations

N = int(input())

S = [list(map(int, input().split())) for _ in range(N)]

# 최소값을 초기화
min_score = float('inf')

# 조합으로 팀을 만든다.
teams = list(combinations(range(N), N // 2))
# 팀들의 조합중 뽑을 수 있는 두개의 팀을 만든다.
for combination in teams:
    team_start = list(combination)
    team_link = list(set(range(N)) - set(team_start))
    #print(team_start, team_link)

    # 능력치 합
    ability_start = sum(S[i][j] for i in team_start for j in team_start)
    ability_link = sum(S[i][j] for i in team_link for j in team_link)
    # min_score과 각팀의 능력치 차이 중 더 작은것으로 갱신한다.
    min_score = min(min_score, abs(ability_start - ability_link))

print(min_score)