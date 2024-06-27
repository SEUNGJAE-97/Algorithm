def main():
    N = int(input())
    arr = [list(map(int, input().split())) for _ in range(N)]
    arr.sort(key=lambda x: x[1], reverse=True)
    
    # 초기 값 지정
    my_t = arr[0][1]
    for i in range(N):
        # 해야할 과제의 마감일과 여유시간을 비교하고, 해야할 과제의 소요시간을 제외한다.
        # 두번째 과제를 진행하기 위해서는 최소 7(10-3)일부터 시작을 해야함
        my_t = min(arr[i][1], my_t) - arr[i][0]
    print(my_t)



if __name__ == '__main__':
    main()