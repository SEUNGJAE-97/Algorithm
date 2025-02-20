import java.io.*;
import java.util.*;

public class Solution {
  private static int N;
  private static String[] arr;
  private static Stack<Character> stack;

  public static void main(String[] args) throws Exception {
    // System.setIn(new FileInputStream("input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    for (int tc = 1; tc <= 10; tc++) {
      // init
      N = Integer.parseInt(br.readLine().trim());
      stack = new Stack<>();
      String input = br.readLine().trim();
      StringBuilder result = new StringBuilder();

      // 중위 연산식을 후위 연산식으로 변환
      for (char c : input.toCharArray()) {
        // 숫자는 그대로 결과에 추가
        if (Character.isDigit(c)) {
          result.append(c);
        }
        // 왼쪽 괄호는 스택에 넣기
        else if (c == '(') {
          stack.push(c);
        }
        // 오른쪽 괄호가 오면, 왼쪽 괄호까지 연산자 스택에서 꺼내기
        else if (c == ')') {
          while (!stack.isEmpty() && stack.peek() != '(') {
            result.append(stack.pop());
          }
          stack.pop(); // '(' 꺼내기
        }
        // 연산자 처리
        else if (c == '+' || c == '*') {
          // '*'은 '+'보다 우선순위가 높으므로 스택에서 처리
          while (!stack.isEmpty() && stack.peek() == '*' && c == '+') {
            result.append(stack.pop());
          }
          stack.push(c);
        }
      }

      // 스택에 남은 연산자는 다 결과에 추가
      while (!stack.isEmpty()) {
        result.append(stack.pop());
      }

      // 후위 연산식을 계산
      int calculationResult = evaluatePostfix(result.toString());
      sb.append("#").append(tc).append(" ").append(calculationResult).append("\n");
    }

    System.out.println(sb);
  }

  // 후위 연산식 계산 함수
  private static int evaluatePostfix(String postfix) {
    Stack<Integer> stack = new Stack<>();

    for (char c : postfix.toCharArray()) {
      if (Character.isDigit(c)) {
        stack.push(c - '0'); // 숫자는 스택에 넣기
      } else {
        int b = stack.pop();
        int a = stack.pop();

        switch (c) {
          case '+':
            stack.push(a + b);
            break;
          case '*':
            stack.push(a * b);
            break;
        }
      }
    }

    return stack.pop(); // 최종 결과 반환
  }
}