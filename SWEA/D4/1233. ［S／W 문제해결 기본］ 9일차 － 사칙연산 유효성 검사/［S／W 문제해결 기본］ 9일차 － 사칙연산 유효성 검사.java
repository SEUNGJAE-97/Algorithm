import java.io.*;
import java.util.*;

public class Solution {
  private static Map<Integer, String[]> nodeData = new HashMap<>();
  private static int N;

  public static void main(String[] args) throws Exception {
    //System.setIn(new FileInputStream("input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    for (int tc = 1; tc <= 10; tc++) {
      N = Integer.parseInt(br.readLine());
      nodeData.clear();

      for (int i = 0; i < N; i++) {
        String[] parts = br.readLine().split(" ");
        int num = Integer.parseInt(parts[0]);
        String value = parts[1];
        int left = (parts.length > 2) ? Integer.parseInt(parts[2]) : -1;
        int right = (parts.length > 3) ? Integer.parseInt(parts[3]) : -1;

        nodeData.put(num, new String[] {value, String.valueOf(left), String.valueOf(right)});
      }

      int result = isValidExpressionTree() ? 1 : 0;
      sb.append("#").append(tc).append(" ").append(result).append("\n");
    }

    System.out.println(sb);
  }

  private static boolean isValidExpressionTree() {
    for (int i = 1; i <= N; i++) {
      String[] data = nodeData.get(i);
      String value = data[0];
      int left = Integer.parseInt(data[1]);
      int right = Integer.parseInt(data[2]);

      boolean isOperator =
          value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/");
      boolean isNumber = value.matches("\\d+"); // 숫자인지 확인

      if (isOperator) {
        // 연산자는 두 개의 자식을 가져야 함
        if (left == -1 || right == -1) {
          return false;
        }
      } else if (isNumber) {
        // 숫자는 자식이 없어야 함
        if (left != -1 || right != -1) {
          return false;
        }
      } else {
        // 연산자도 숫자도 아니면 잘못된 입력
        return false;
      }
    }
    return true;
  }
}