def solution(progresses, speeds):
    answer = []
    for i in range(len(progresses)):
        remaining = 100 - progresses[i]
        days = (remaining + speeds[i] - 1) // speeds[i]  # 올림 계산
        answer.append(days)
    
    result = []
    max_day = answer[0]  # 첫 번째 작업을 현재 배포 기준일로 설정
    count = 1
    for i in range(1, len(answer)):
        if answer[i] <= max_day:
            count += 1
        else:
            result.append(count)
            max_day = answer[i]
            count = 1
    result.append(count)
    
    return result
