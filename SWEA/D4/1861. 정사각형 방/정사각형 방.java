import java.io.*;
import java.util.*;

public class Solution {
  private static int N;
  private static int[] dx = {-1, 0, 1, 0};
  private static int[] dy = {0, 1, 0, -1};
  private static int[][] arr;
  private static int maxAddr;
  private static int maxDist;

  private static int explore(int n, int m) {
    int distance = 1;
    for (int d = 0; d < 4; d++) {
      int nx = n + dx[d];
      int ny = m + dy[d];
      if (nx >= 0 && nx < N && ny >= 0 && ny < N && arr[nx][ny] == arr[n][m] + 1) {
        distance = Math.max(distance, 1 + explore(nx, ny));
      }
    }
    return distance;
  }

  private static void calc() {
    maxAddr = Integer.MAX_VALUE;
    maxDist = 0;

    for (int n = 0; n < N; n++) {
      for (int m = 0; m < N; m++) {
        int dist = explore(n, m);
        if (dist > maxDist || (dist == maxDist && arr[n][m] < maxAddr)) {
          maxDist = dist;
          maxAddr = arr[n][m];
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    //System.setIn(new FileInputStream("input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine().trim());
    for (int tc = 1; tc <= T; tc++) {
      N = Integer.parseInt(br.readLine().trim());
      arr = new int[N][N];

      for (int i = 0; i < N; i++) {
        String[] split = br.readLine().trim().split(" ");
        for (int j = 0; j < N; j++) {
          arr[i][j] = Integer.parseInt(split[j]);
        }
      }

      calc();
      sb.append("#").append(tc).append(" ").append(maxAddr).append(" ").append(maxDist)
          .append("\n");
    }
    System.out.println(sb);
  }
}