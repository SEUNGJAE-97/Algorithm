def main():
    N = int(input())
    arr = [list(map(int, input().split())) for _ in range(N)]
    sorted(arr, reverse=True)

    for i in arr:
        answer = 1
        for j in arr:
            if i[0] < j[0] and i[1] < j[1]:
                answer += 1
        print(answer, end=' ')


if __name__ == '__main__':
    main()