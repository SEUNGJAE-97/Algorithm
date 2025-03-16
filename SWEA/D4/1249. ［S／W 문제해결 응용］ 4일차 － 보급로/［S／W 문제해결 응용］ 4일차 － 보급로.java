import java.io.*;
import java.util.*;

public class Solution {
	/* 최단경로문제 bfs로 풀기 */
	private static int T, N;
	private static int INF = 1000000;
	private static int[][] map, distance;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };

	private static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	private static void bfs(int x, int y) {
		Deque<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(x, y));
		distance[x][y] = map[x][y];

		while (!queue.isEmpty()) {
			Node current = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];

				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (distance[nx][ny] > distance[current.x][current.y] + map[nx][ny]) {
						distance[nx][ny] = distance[current.x][current.y] + map[nx][ny];
						queue.offer(new Node(nx, ny));
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			distance = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				String split = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = split.charAt(j) - '0';
					distance[i][j] = Integer.MAX_VALUE;
				}
			}
			bfs(0, 0);
			sb.append("#").append(tc).append(" ").append(distance[N - 1][N - 1]).append("\n");
		}
		System.out.println(sb);
	}
}