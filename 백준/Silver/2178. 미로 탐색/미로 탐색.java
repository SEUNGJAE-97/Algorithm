import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
  private static int N;
  private static int M;
  private static Queue<Point> queue;
  private static int[] dx = {-1, 1, 0, 0};
  private static int[] dy = {0, 0, -1, 1};
  private static int[][] MIRO;
  private static boolean[][] V;

  private static void bfs(int x, int y) {
    queue = new LinkedList<>();
    queue.offer(new Point(x, y));
    V[x][y] = true;

    while (!queue.isEmpty()) {
      Point cur = queue.poll();
      // 사방탐색
      for (int i = 0; i < 4; i++) {
        int nextX = cur.x + dx[i];
        int nextY = cur.y + dy[i];

        if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M)
          continue;
        if (MIRO[nextX][nextY] == 0)
          continue;
        if (V[nextX][nextY])
          continue;

        queue.offer(new Point(nextX, nextY));
        V[nextX][nextY] = true;

        MIRO[nextX][nextY] = MIRO[cur.x][cur.y] + 1;

      }
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    MIRO = new int[N][M];
    V = new boolean[N][M];

    for (int i = 0; i < N; i++) {
      String split = br.readLine();
      for (int j = 0; j < M; j++) {
        MIRO[i][j] = split.charAt(j) - '0';
      }
    }
    bfs(0, 0);
    System.out.println(MIRO[N - 1][M - 1]);
  }
}