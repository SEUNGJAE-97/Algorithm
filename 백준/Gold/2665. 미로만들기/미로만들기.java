import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[][] map;
	private static Deque<Node> queue;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int[][] dist;
	private static final int INF = 999999;

	private static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	private static void bfs() {
		queue = new ArrayDeque<>();
		queue.addFirst(new Node(0, 0));
		dist[0][0] = 0;

		while (!queue.isEmpty()) {
			Node cur = queue.pollFirst();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
					int cost = dist[cur.x][cur.y] + (map[nx][ny] == 0 ?  1 : 0);
					if(cost < dist[nx][ny]) {
						dist[nx][ny] = cost;
						if (map[nx][ny] == 1) queue.offerLast(new Node(nx, ny));
						else queue.offerFirst(new Node(nx, ny));
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dist = new int[N][N];
		
		for(int[] row  : dist) Arrays.fill(row, INF);

		for (int i = 0; i < N; i++) {
			String split = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = (char) (split.charAt(j) - '0');
			}
		}
		bfs();
		System.out.println(dist[N-1][N-1] == INF ? -1 : dist[N-1][N-1]);
	}
}