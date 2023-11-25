N = int(input())
Array_A = [0 for _ in range(N)]
Array_B = list(map(int, input().split()))
count = 0
while True:
    if all(x == 0 for x in Array_B):
        break
    if all(x % 2 == 0 for x in Array_B):
        Array_B = [x // 2 for x in Array_B]
        #print(Array_B)
        count += 1
    else:
        for i in range(N):
            if Array_B[i] % 2 == 1:
                Array_B[i] -= 1
                #print(Array_B)
                count += 1
print(count)