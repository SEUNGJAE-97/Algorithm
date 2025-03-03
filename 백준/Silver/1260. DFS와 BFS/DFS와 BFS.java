import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, V;
	private static List<Integer>[] graph;
	private static boolean[] visited;

	public static void DFS(int start) {
		Deque<Integer> stack = new ArrayDeque<>();
		// 초기 값 설정
		stack.push(start);
		visited[start] = true;

		System.out.print(start);
		System.out.print(" ");
		while (!stack.isEmpty()) {
			int current = stack.peek();
			boolean hasNext = false;

			for (int next : graph[current]) {
				if (!visited[next]) {
					stack.push(next);
					visited[next] = true;
					hasNext = true;
					System.out.print(next);
					System.out.print(" ");
					break;
				}
			}
			if (!hasNext) {
				stack.pop();
			}
		}
	}

	private static void BFS(int v) {
		// TODO Auto-generated method stub
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(v);
		visited[v] = true;
		System.out.print(v);
		System.out.print(" ");
		while (!queue.isEmpty()) {
			int current = queue.poll();

			for (int next : graph[current]) {
				if (!visited[next]) {
					queue.offer(next);
					visited[next] = true;
					System.out.print(next);
					System.out.print(" ");
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

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

		for (int i = 1; i <= N; i++) {
			Collections.sort(graph[i]);
		}

		// DFS 실행
		visited = new boolean[N + 1];
		DFS(V);
		System.out.println();
		visited = new boolean[N + 1];
		BFS(V);

	}

}