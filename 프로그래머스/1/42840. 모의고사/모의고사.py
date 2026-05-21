def solution(answers):
    p1 = [1, 2, 3, 4, 5]
    p2 = [2, 1, 2, 3, 2, 4, 2, 5]
    p3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    answer = [0,0,0]
    for i in range(len(answers)):
        if answers[i] == p1[i % len(p1)]:
            answer[0] += 1
        if answers[i] == p2[i % len(p2)]:
            answer[1] += 1
        if answers[i] == p3[i % len(p3)]:
            answer[2] += 1
    
    max_score = max(answer)
    
    result = []
    for idx, score in enumerate(answer):
        if score == max_score:
            result.append(idx + 1)
            
    return result
