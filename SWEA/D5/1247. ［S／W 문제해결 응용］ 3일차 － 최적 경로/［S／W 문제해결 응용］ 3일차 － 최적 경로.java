import java.io.*;
import java.util.*;

// 1247 최적경로
public class Solution {
  private static int T, N;
  private static Node[] customer;
  private static Node Home, Company;
  private static int result;
  private static boolean[] visited;

  private static void dfs(int depth, int prevX, int prevY, int distance) {
    // 종료조건
    if (depth == N) {
      distance += Math.abs(prevX - Home.x) + Math.abs(prevY - Home.y);
      result = Math.min(distance, result);
      return;
    }
    for (int n = 0; n < N; n++) {
      if (!visited[n]) {
        // 방문처리
        visited[n] = true;

        int dist = Math.abs(prevX - customer[n].x) + Math.abs(prevY - customer[n].y);
        // 재귀
        dfs(depth + 1, customer[n].x, customer[n].y, distance+dist);

        // 다시 복구
        visited[n] = false;
      }

    }
  }

  static class Node {
    int x;
    int y;

    public Node(int x, int y) {
      super();
      this.x = x;
      this.y = y;
    }
  }


  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    // 0. 데이터 입력
    T = Integer.parseInt(br.readLine().trim());

    for (int tc = 1; tc <= T; tc++) {
      N = Integer.parseInt(br.readLine().trim());
      result = Integer.MAX_VALUE;
      customer = new Node[N];
      visited = new boolean[N];

      String[] split = br.readLine().split(" ");

      Company = new Node(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
      Home = new Node(Integer.parseInt(split[2]), Integer.parseInt(split[3]));

      for (int i = 0, idx = 4; i < N; i++, idx += 2) {
        customer[i] = new Node(Integer.parseInt(split[idx]), Integer.parseInt(split[idx + 1]));
      }
      // 1. dfs탐색
      // depth, prevX, prevY, distance
      dfs(0, Company.x, Company.y, 0);
      sb.append("#").append(tc).append(" ").append(result).append("\n");
    }

    System.out.println(sb);
  }
}
