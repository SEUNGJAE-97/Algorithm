N, K = map(int, input().split())

arr = list(map(int, input().split()))

diff = []

for i in range(N-1):
    diff.append(abs(arr[i] - arr[i+1]))

diff.sort()

print(sum(diff[0:N-K]))