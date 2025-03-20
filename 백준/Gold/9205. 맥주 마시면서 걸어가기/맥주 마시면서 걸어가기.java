import java.io.*;
import java.util.*;

public class Main {
	private static int T, N;
	private static int start_x, start_y, end_x, end_y;
	private static Deque<Node> queue;
	private static List<Node> list;
	private static boolean[] visited;
	
	private static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
		

	}
	private static void bfs(int index) {
		queue.offer(list.get(index));
		visited[index] = true;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			if(current.x == end_x && current.y == end_y) {
				System.out.println("happy");
				return;
			}
			for(int i = 1; i <= N+1; i++) {
				if(!visited[i] && distance_check(current, list.get(i))) {
					visited[i] = true;
					queue.offer(new Node(list.get(i).x, list.get(i).y));
				}
			}
		}
		
		System.out.println("sad");
	}
	
	private static boolean distance_check(Node n1, Node n2) {
		int result = Math.abs(n1.x - n2.x) + Math.abs(n1.y - n2.y);
		if(result <= 1000) return true;
		else return false;
		
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			visited = new boolean[N+2];
			queue = new ArrayDeque<>();
			list = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			start_x = Integer.parseInt(st.nextToken());
			start_y = Integer.parseInt(st.nextToken());
			list.add(new Node(start_x, start_y));
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				list.add(new Node(x, y));
			}
			
			st = new StringTokenizer(br.readLine());
			end_x = Integer.parseInt(st.nextToken());
			end_y = Integer.parseInt(st.nextToken());
			list.add(new Node(end_x, end_y));
			bfs(0);
		}
	}
}
