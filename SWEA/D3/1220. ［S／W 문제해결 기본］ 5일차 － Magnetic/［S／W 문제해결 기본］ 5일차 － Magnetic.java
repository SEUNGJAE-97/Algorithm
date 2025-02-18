import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
  private static int garosero(int[][] array) {
    int count = 0;
    for (int i = 0; i < 100; i++) {
      boolean check = false;

      for (int j = 0; j < 100; j++) {
        if (array[j][i] == 1) {
          check = true;
        }
        if (check && array[j][i] == 2) {
          count += 1;
          check = false;
        }
      }
    }

    return count;
  }

  private static int result;

  public static void main(String[] args) throws Exception {
    //System.setIn(new FileInputStream("input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    for (int testCase = 1; testCase <= 10; testCase++) {
      sb.append("#").append(testCase).append(" ");
      // init
      result = 0;

      int N = Integer.parseInt(br.readLine());
      int[][] array = new int[100][100];

      for (int i = 0; i < 100; i++) {
        String[] line = br.readLine().split(" ");
        for (int j = 0; j < 100; j++) {
          array[i][j] = Integer.parseInt(line[j]);
        }
      }
      // 연산
      result = garosero(array);
      sb.append(result).append("\n");
    }

    System.out.println(sb);
  }
}