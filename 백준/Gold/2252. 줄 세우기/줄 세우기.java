import java.io.*;
import java.util.*;

public class Main {
	private static class Node {

		public int vertex;
		public Node Link;

		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.Link = link;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", Link=" + Link + "]";
		}
	}

	private static int V, E; // 정점과 간선 개수
	private static Node[] adjList;
	private static int[] inDegree; // 인덱스 번호 : 정점 번호, 값 : 진입차수


	private static List<Integer> topologySort() {
		List<Integer> orderList = new ArrayList<>();
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= V; i++) {
			// 1. 진입 차수가 0인 노드를 큐에 모두 삽입
			if (inDegree[i] == 0)
				queue.offer(i);
		}

		while (!queue.isEmpty()) {
			// 2. 큐에서 진입 차수가 0인 노드를 꺼내어 자신과 인접한 노드의 간선을 제거한다.
			Integer cur = queue.poll();
			orderList.add(cur);

			for (Node temp = adjList[cur]; temp != null; temp = temp.Link) {
				// 3. 간선 제거 후 진입차수가 0이 된 노드를 큐에 넣는다.
				if (--inDegree[temp.vertex] == 0)
					queue.offer(temp.vertex);
			}
		}
		return orderList;
	}

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 여러분의 알고리즘 코드 작성하기
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adjList = new Node[V + 1];
		inDegree = new int[V + 1];

		int from, to;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, adjList[from]);
			inDegree[to]++;
		}
		// 2. 위상정렬 수행
		List<Integer> list = topologySort();

		if (list.size() == V) {
			for (Integer vertex : list) {
				System.out.print(vertex + " ");
			}
		}
	}
}