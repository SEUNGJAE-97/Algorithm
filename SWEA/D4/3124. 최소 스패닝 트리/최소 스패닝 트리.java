import java.io.*;
import java.util.*;

public class Solution {
	private static int V, E, T;
	private static List<Node>[] adjList;
	private static int[] Edge;
	private static boolean[] Visited;

	private static class Node implements Comparable<Node> {
		public int vertex;
		public int weight;

		public Node(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}

	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			// 간선, 정점 개수 입력
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			adjList = new ArrayList[V];
			for (int i = 0; i < V; i++) {
				adjList[i] = new ArrayList<>();
			}

			Edge = new int[V];
			Visited = new boolean[V];

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken())-1;
				int to = Integer.parseInt(st.nextToken())-1;
				int weight = Integer.parseInt(st.nextToken());

				adjList[from].add(new Node(to, weight));
				adjList[to].add(new Node(from, weight));
			}

			Arrays.fill(Edge, Integer.MAX_VALUE);

			// 구현
			Edge[0] = 0;
			long cost = 0;
			int vertexCount = 0;
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(0, 0));

			while (!pq.isEmpty()) {
				Node current = pq.poll();
				if(Visited[current.vertex]) continue;
				int v = current.vertex;
				int w = current.weight;

				if (Visited[v])
					continue;
				Visited[v] = true;
				cost += w;

				if (++vertexCount == V) {
					break;
				}

				for (Node next : adjList[v]) {
					if (!Visited[next.vertex]) {
						pq.add(new Node(next.vertex, next.weight));
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(cost).append("\n");
		}
		System.out.println(sb);
	}
}