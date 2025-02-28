import java.util.*;
import java.io.*;

public class Main {
	private static int V, E, Start;
	private static List<Node>[] adjList;
	private static boolean[] visited;
	private static int[] distance;
	private static final int INF = 999999;
	private static PriorityQueue<Node> pq;

	private static class Node implements Comparable<Node>{
		public int vertex;
		public int weight;

		public Node(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Node [Vertex=" + vertex + " , weight=" + weight + "]";
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
	}

	private static void Dijkstra() {
		StringBuilder sb = new StringBuilder();
		pq = new PriorityQueue<>();
		// 시작점은 가중치가 0임
		pq.add(new Node(Start, 0));
		// 시작점에서 갈 수 있는 정점의 가중치를 distance 배열에 반영
		while (!pq.isEmpty()) {
			Node current = pq.poll();
			int v = current.vertex;
			int w = current.weight;
						
			// 이미 방문한 정점은 무시
			if(visited[v]) continue;
			
			// 현재 위치 방문처리
			visited[v] = true;
			
			
			for (Node next : adjList[v]) {
				// 방문하지 않았고 더 나은 최적의 해가 있다면
				if(!visited[next.vertex] && distance[next.vertex] > distance[v]+next.weight) {
					// 더 나은 해로 distance배열 업데이트
					distance[next.vertex] = distance[v]+next.weight;
					pq.add(new Node(next.vertex, distance[next.vertex]));
					//System.out.println(pq);
				}
			}
		}
		// 도달할수없다면
		for (int i = 0; i < V; i++) {
		    if (distance[i] == INF) sb.append("INF\n");
		    else sb.append(distance[i]).append("\n");
		}
		System.out.print(sb);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		Start = Integer.parseInt(br.readLine())-1;

		adjList = new ArrayList[V];

		for (int i = 0; i < V; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int cnt = 0; cnt < E; cnt++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());

			adjList[from].add(new Node(to, weight));
		}
		// 초기 작업
		visited = new boolean[V];// 방문처리배열
		distance = new int[V]; // 거리 저장할 배열
		Arrays.fill(distance, INF); // 모두 무한으로 초기화
		distance[Start] = 0;// 시작점은 가중치 0으로 초기화

		// 다익스트라 알고리즘 적용 , 시작점을 매개변수로 넘겨줌
		Dijkstra();
	}
}