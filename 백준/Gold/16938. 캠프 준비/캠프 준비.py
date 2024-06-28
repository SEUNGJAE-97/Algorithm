from itertools import combinations


def main():
    N, L, R, X = map(int, input().split())
    problem = list(map(int, input().split()))
    result = 0

    for i in range(2, N + 1):
        for combination in combinations(problem, i):
            if L <= sum(combination) <= R:
                if max(combination) - min(combination) >= X:
                    result += 1

    print(result)


if __name__ == '__main__':
    main()