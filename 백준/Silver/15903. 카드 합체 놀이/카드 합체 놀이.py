n, m = map(int, input().split())

array = list(map(int, input().split()))
ans = 0
for i in range(m):
    array.sort()
    key = array[0] + array[1]
    array[0], array[1] = key, key

ans = sum(array)
print(ans)