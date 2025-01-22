import heapq


def main():
    N = int(input())

    arr = sorted([list(map(int, input().split())) for _ in range(N)])

    result = []

    for deadline, score in arr:
        if len(result) < deadline:
            heapq.heappush(result, score)
        elif result and result[0] < score:
            heapq.heapreplace(result, score)
    print(sum(result))


if __name__ == '__main__':
    main()