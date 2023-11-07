"""
40021 -> 4002
4002 // 2 -> 2001
2001 -> 200
200 // 2 -> 100 == A ?

끝자리가 1이면 제거하고, 아니라면 2로 나누고
매번 그 결과값이 A와 같은지 체크한다.

끝자리가 1이 아닌지는 2로 나눌 수 있는지로 체크하기

"""
A, B = map(int, input().split())
cnt = 0

while True:
    if B % 2 == 0 and A < B:
        B //= 2
    elif B % 2 == 1 and A < B:
        num_list = list(map(int, str(B)))
        if num_list[-1] != 1:
            print(-1)
            break
        else:
            # 마지막 요소 제거
            num_list.pop()
            B = int("".join(map(str, num_list)))
    cnt += 1
    if A == B:
        print(cnt+1)
        break
    elif A != B and A >= B:
        print(-1)
        break