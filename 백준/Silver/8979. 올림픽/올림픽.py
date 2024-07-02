def main():
    N, K = map(int, input().split())
    medal = [list(map(int, input().split())) for _ in range(N)]

    medal.sort(key=lambda x: (x[1], x[2], x[3]), reverse=True)

    index = [medal[i][0] for i in range(N)].index(K)

    print(index)



if __name__ == '__main__':
    main()