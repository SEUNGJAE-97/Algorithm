import java.util.*;
import java.io.*;

public class Solution {


  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    // System.setIn(new FileInputStream("input.txt"));
    int T = 10;
    for (int test_case = 1; test_case <= T; test_case++) {
      sb.append("#").append(test_case).append(" ");
      int N = Integer.parseInt(in.readLine());
      int[] building = new int[N];

      String[] dummy = in.readLine().split(" ");
      for (int i = 0; i < N; i++) {
        building[i] = Integer.parseInt(dummy[i]);
      }

      int totalView = 0;

      for (int i = 2; i < N - 2; i++) {
        int left1 = building[i - 2];
        int left2 = building[i - 1];
        int right1 = building[i + 1];
        int right2 = building[i + 2];

        int max = Math.max(Math.max(left1, left2), Math.max(right1, right2));

        if (building[i] > max) {
          totalView += building[i] - max;
        }
      }
      sb.append(totalView).append("\n");
    }
    System.out.println(sb);
  }
}