def main():
    N = int(input())
    arr = [list(map(int, input().split())) for _ in range(N)]
    arr.sort(key=lambda x: x[1], reverse=True)
    result = arr[0][1]

    for i in range(N):
        next_need_time, next_dead_line = arr[i]
        if result >= next_dead_line:
            result = next_dead_line - next_need_time
        else:
            result -= next_need_time

    if result < 0:
        print(-1)
    else:
        print(result)


if __name__ == '__main__':
    main()