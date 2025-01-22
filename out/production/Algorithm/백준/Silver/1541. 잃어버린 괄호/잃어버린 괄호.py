"""
적절하게 괄호를 쳐서 가장 작은 값을 유추하는것.

해결 방법:
55 - 50 + 40 -> 55 - (50 + 40)
10 + 20 + 30 + 40 -> 동일

마이너스가 나오기 직전까지의 모든 수를 더해주고, 마이너스가 나온 이후의 숫자는
괄호를 쳐준다.
"""
# +가 나오는 모든 수를 더하는 함수 plus
def plus(a):
    ans = a.split('+')
    num = 0
    for i in ans:
        num += int(i)
    return num
answer = 0

# -를 기준으로 math에 저장한다.
math = input().split('-')

for j in range(len(math)):
    # 첫번째 숫자는 answer을 초기화
    if j == 0:
        answer = plus(math[j])
    else:
        answer -= plus(math[j])

print(answer)