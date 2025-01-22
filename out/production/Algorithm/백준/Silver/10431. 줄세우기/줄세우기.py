def main():
    P = int(input())

    for _ in range(P):
        answer = 0
        TestCase = list(map(int, input().split()))

        for i in range(1,len(TestCase)-1):
            for j in range(i+1,len(TestCase)):
                if TestCase[i] > TestCase[j]:
                    TestCase[i], TestCase[j] = TestCase[j], TestCase[i]
                    answer += 1

        print(TestCase[0], answer)


if __name__ == '__main__':
    main()