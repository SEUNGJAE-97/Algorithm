from itertools import permutations

def custom_divide(a, b):
    if a >= 0:
        return a // b
    else:
        return -(-a // b)
def custom_calculate(expression):
    result = int(expression[0])  # 초기값 설정

    for i in range(1, len(expression), 2):
        operator = expression[i]
        operand = int(expression[i + 1])

        if operator == '+':
            result += operand
        elif operator == '-':
            result -= operand
        elif operator == '*':
            result *= operand
        elif operator == '/':
            # 나눗셈은 custom_divide 함수를 사용
            result = custom_divide(result, operand)

    return result
N = int(input())
array = list(map(int, input().split()))

# 연산자는 +, -, *, / 순인 리스트 operator
operator_num = list(map(int, input().split()))
operator_list = ['+', '-', '*', '/']
operator = []
for i in range(len(operator_num)):
    for _ in range(operator_num[i]):
        operator.append(operator_list[i])

nPr = list(permutations(operator, len(operator)))

max_result = float('-inf')  # 최댓값 초기화
min_result = float('inf')   # 최솟값 초기화

for n in range(len(nPr)):
    expression = [str(array[0])]
    for i, op in enumerate(nPr[n]):
        expression.append(op)
        expression.append(str(array[i + 1]))

    result = custom_calculate(expression)
    #print(expression)
    # 최댓값, 최솟값 업데이트
    max_result = max(max_result, result)
    min_result = min(min_result, result)

print(max_result)
print(min_result)
