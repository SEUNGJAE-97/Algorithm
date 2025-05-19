import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


// swea 5521
public class Solution {
  private static void BFS() {
    visited[1] = true;
    Queue<Integer> queue = new ArrayDeque<>();

    for (int friend : es[1]) {
      if (!visited[friend]) {
        visited[friend] = true;
        queue.offer(friend);
        result++;
      }
    }

    // BFS
    while (!queue.isEmpty()) {
      int cur = queue.poll();

      for (int friend : es[cur]) {
        if (!visited[friend]) {
          visited[friend] = true;
          result++;
        }
      }
    }
  }

  static int T, N, M, result;
  static List<Integer>[] es;
  static List<Integer> selects;
  static boolean[] visited;
  static int cnt;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();
    T = Integer.parseInt(br.readLine().trim());

    for (int tc = 1; tc <= T; tc++) {
      // N, M 입력
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      result = 0;
      es = new ArrayList[N + 1];
      selects = new ArrayList<Integer>();
      visited = new boolean[N + 1];

      for (int i = 0; i < N + 1; i++) {
        es[i] = new ArrayList<Integer>();
      }

      for (int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        // 양방향 그래프
        es[a].add(b);
        es[b].add(a);

      }
      BFS();
      sb.append("#").append(tc).append(" ").append(result).append("\n");
      // System.out.println(Arrays.toString(es));
    }
    System.out.println(sb);
  }
}
// 생일파티
