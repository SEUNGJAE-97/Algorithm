import java.util.*;
import java.io.*;

public class Main {
  static int N, M;
  static ArrayList<Integer>[] graph;
  static boolean[] visited;
  static boolean found = false;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    graph = new ArrayList[N];
    for (int i = 0; i < N; i++) {
      graph[i] = new ArrayList<>();
    }

    // node
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph[a].add(b);
      graph[b].add(a);
    }

    for (int i = 0; i < N; i++) {
      visited = new boolean[N];
      dfs(i, 0);
      if (found)
        break;
    }
    System.out.println(found ? 1 : 0);
  }

  private static void dfs(int node, int depth) {
    if (depth == 4) { // 깊이 4 도달하면 정답
      found = true;
      return;
    }

    visited[node] = true;

    for (int next : graph[node]) {
      if (!visited[next]) {
        dfs(next, depth + 1);
        if (found)
          return;
      }
    }

    visited[node] = false;
  }
}