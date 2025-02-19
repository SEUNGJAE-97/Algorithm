import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
  private static int[] start;
  private static int[] dx = {-1, 0, 1, 0};
  private static int[] dy = {0, 1, 0, -1};
  private static boolean[][] V;
  private static int result;
  static Deque<int[]> stack = new ArrayDeque<>();

  // dfs 구현
  public static void MIRO(int[][] array) throws Exception {
    stack.clear();
    stack.addFirst(start);
    V[start[0]][start[1]] = true;

    while (!stack.isEmpty()) {
      int[] current = stack.removeFirst();
      int x = current[0], y = current[1];

      // 방문
      if (array[x][y] == 3) {
        result = 1;
        return;
      }
      // 탐색
      for (int d = 0; d < 4; d++) {
        int nx = x + dx[d];
        int ny = y + dy[d];
        if (nx >= 0 && nx < 16 && ny >= 0 && ny < 16 && array[nx][ny] != 1 && !V[nx][ny]) {
          stack.addFirst(new int[] {nx, ny});
          V[nx][ny] = true;
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    //System.setIn(new FileInputStream("input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    for (int testCase = 1; testCase <= 10; testCase++) {
      sb.append("#").append(testCase).append(" ");
      // init
      br.readLine();
      int[][] array = new int[16][16];
      V = new boolean[16][16];
      start = new int[2];

      for (int i = 0; i < 16; i++) {
        String[] line = br.readLine().split("");
        for (int j = 0; j < 16; j++) {
          array[i][j] = Integer.parseInt(line[j]);
          if (array[i][j] == 2) {
            start[0] = i;
            start[1] = j;
          }
        }
      }
      // 연산
      result = 0;
      MIRO(array);
      sb.append(result).append("\n");
    }

    System.out.println(sb);

  }
}