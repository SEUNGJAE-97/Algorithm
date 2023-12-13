def count_zeros(n):
    count = 0
    divisor = 5
    while n // divisor > 0:
        count += n // divisor
        divisor *= 5
    return count

def find_smallest_n(m):
    start, end = 1, 5 * m
    result = -1

    while start <= end:
        mid = (start + end) // 2
        zeros = count_zeros(mid)

        if zeros < m:
            start = mid + 1
        elif zeros >= m:
            result = mid
            end = mid - 1

    return result if count_zeros(result) == m else -1

# 입력 받기
M = int(input())

# 결과 출력
answer = find_smallest_n(M)
print(answer)