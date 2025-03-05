import java.io.*;
import java.util.*;

public class Main {
	private static int T, N;
	private static ArrayList<Node> tree;
	private static Deque<Node> stack;
	private static StringBuilder sb = new StringBuilder();

	private static class Node {
		int value;
		ArrayList<Node> children; // 자식 노드를 저장할 리스트
		Node parent;

		public Node(int value) {
			this.value = value;
			this.children = new ArrayList<>();
			this.parent = null;
		}

		public void addChild(Node child) {
			child.parent = this;
			children.add(child);
		}
	}

	private static int DFS(Node startX, Node startY) {
		Set<Integer> visited = new HashSet<>();

		while (startX != null) {
			visited.add(startX.value);
			startX = startX.parent;
		}
		while (startY != null) {
			if (visited.contains(startY.value)) {

				return startY.value;
			}
			startY = startY.parent;
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			tree = new ArrayList<>();

			for (int i = 0; i < N + 1; i++) {
				tree.add(new Node(i));
			}

			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				tree.get(from).addChild(tree.get(to));
			}
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int Result = DFS(tree.get(startX), tree.get(startY));
			
			sb.append(Result).append("\n");
		}
		System.out.println(sb);
	}
}