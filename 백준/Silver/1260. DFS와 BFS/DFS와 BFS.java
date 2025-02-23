import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static boolean[] V;
  private static List<Integer>[] graph;
  private static StringBuilder sb;

  // DFS (Stack 사용)
  public static void DFS(int start) {
    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(start);
    V[start] = true;
    sb.append(start).append(" ");

    while (!stack.isEmpty()) {
      int current = stack.peek();
      boolean hasNext = false;

      for (int next : graph[current]) {
        if (!V[next]) {
          stack.push(next);
          V[next] = true;
          sb.append(next).append(" ");
          hasNext = true;
          break; // 한 번에 한 개씩 탐색 (DFS)
        }
      }
      if (!hasNext)
        stack.pop(); // 더 이상 탐색할 노드 없으면 백트래킹
    }
  }

  // BFS (Queue 사용)
  public static void BFS(int start) {
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(start);
    V[start] = true;
    sb.append(start).append(" ");

    while (!queue.isEmpty()) {
      int current = queue.poll();

      for (int next : graph[current]) {
        if (!V[next]) {
          queue.offer(next);
          V[next] = true;
          sb.append(next).append(" ");
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int V_start = Integer.parseInt(st.nextToken());

    // 그래프 초기화
    graph = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) {
      graph[i] = new ArrayList<>();
    }

    // 입력 받기
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());

      graph[u].add(v);
      graph[v].add(u); // 양방향 그래프
    }

    // 번호 작은 것부터 방문하도록 정렬
    for (int i = 1; i <= N; i++) {
      Collections.sort(graph[i]);
    }

    sb = new StringBuilder();

    // DFS 실행
    V = new boolean[N + 1];
    DFS(V_start);
    sb.append("\n");

    // BFS 실행
    V = new boolean[N + 1];
    BFS(V_start);

    System.out.println(sb);
  }
}