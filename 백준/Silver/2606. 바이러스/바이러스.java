import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	private static int V, E, result;
	private static Deque<Integer> stack;
	private static boolean[] visited;
	private static int[][] computers;

	private static void BFS(int start) {
		visited[start] = true;
		stack.offer(start);
		result = 0;
		while (!stack.isEmpty()) {
			int current = stack.poll();

			for (int i = 0; i < V; i++) {
				// 방문하지 않았으면서, 간선이 연결된 곳이라면
				if (!visited[i] && computers[current][i] == 1) {
					stack.offer(i);
					visited[i] = true;
					result++;
				}
			}
		}
		System.out.println(result);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		stack = new ArrayDeque<>();
		visited = new boolean[V];
		computers = new int[V][V];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;

			computers[from][to] = computers[to][from] = 1;
		}

		BFS(0);
	}

}