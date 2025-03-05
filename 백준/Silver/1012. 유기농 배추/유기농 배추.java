import java.io.*;
import java.util.*;

public class Main {
	private static int T, M, N, K, cnt;
	private static int[][] map;
	private static Deque<Node> stack;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static StringBuilder sb = new StringBuilder();

	private static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	private static void DFS(int startX, int startY) {
		stack.offer(new Node(startX, startY));
		map[startX][startY] = 0;
		
		while (!stack.isEmpty()) {
			Node current = stack.poll();

			for (int d = 0; d < 4; d++) {
				int nx = dx[d] + current.x;
				int ny = dy[d] + current.y;

				if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 1) {
					map[nx][ny] = 0;
					stack.offer(new Node(nx, ny));
				}
			}
		}
		cnt++;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			stack = new ArrayDeque<>();
			cnt = 0;

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				map[y][x] = 1;
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						DFS(i, j);
					}
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}