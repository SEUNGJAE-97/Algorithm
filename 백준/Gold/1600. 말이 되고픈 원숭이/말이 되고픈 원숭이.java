import java.io.*;
import java.util.*;

public class Main {
	private static int K, W, H;
	private static int result = Integer.MAX_VALUE;
	private static int[][] map;
	private static boolean[][][] visited;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int[] dxx = { -2, -2, -1, -1, 1, 1, 2, 2 };
	private static int[] dyy = { -1, 1, 2, -2, -2, 2, 1, -1 };
	private static Deque<Node> queue = new ArrayDeque<>();

	private static class Node {
		int x;
		int y;
		int k;
		int count;

		public Node(int x, int y, int count, int k) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
			this.k = k;

		}
	}

	private static int bfs(int startX, int startY) {
		queue.offer(new Node(startX, startY, 0, K));
		visited[startY][startX][K] = true;

		while (!queue.isEmpty()) {
			Node current = queue.poll();
			if (current.x == W - 1 && current.y == H - 1) {
				return current.count;
			}
			for (int d = 0; d < 4; d++) {
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];
				if (nx >= 0 && nx < W && ny >= 0 && ny < H && map[ny][nx] != 1 && !visited[ny][nx][current.k]) {
					visited[ny][nx][current.k] = true;
					queue.offer(new Node(nx, ny, current.count + 1, current.k));
				}
			}
			// K의 횟수가 남아있다면, 시도
			if (current.k > 0) {
				for (int d = 0; d < 8; d++) {
					int nx = current.x + dxx[d];
					int ny = current.y + dyy[d];
					if (nx >= 0 && nx < W && ny >= 0 && ny < H && map[ny][nx] != 1 && !visited[ny][nx][current.k - 1]) {
						visited[ny][nx][current.k-1] = true;
						queue.offer(new Node(nx, ny, current.count + 1, current.k - 1));
					}
				}
			}

//			/* Test */
//			printMatrix();
//			System.out.println();
		}
		return result;
	}

	private static void printMatrix() {
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W][K + 1];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = bfs(0, 0);
		if (result == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(result);
	}
}