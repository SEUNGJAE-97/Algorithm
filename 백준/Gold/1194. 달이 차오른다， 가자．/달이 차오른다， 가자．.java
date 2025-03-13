import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, startX, startY;
	private static char[][] Map;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static boolean[][][] visited;
	private static boolean[] keys = new boolean[6];

	private static class Node {
		int x, y, keyState, dist;

		public Node(int x, int y, int keyState, int dist) {
			this.x = x;
			this.y = y;
			this.keyState = keyState;
			this.dist = dist;
		}
	}

	private static void bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(startX, startY, 0, 0));
		visited[startX][startY][0] = true;

		while (!queue.isEmpty()) {
			Node current = queue.poll();

			if (Map[current.x][current.y] == '1') {
				System.out.println(current.dist);
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];
				int newKeyState = current.keyState;

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (Map[nx][ny] == '#')
					continue;

				if ('A' <= Map[nx][ny] && Map[nx][ny] <= 'F') {
					if ((newKeyState & (1 << (Map[nx][ny] - 'A'))) == 0)
						continue;
				}

				if ('a' <= Map[nx][ny] && Map[nx][ny] <= 'f') {
					newKeyState |= (1 << (Map[nx][ny] - 'a'));
				}

				if (!visited[nx][ny][newKeyState]) {
					visited[nx][ny][newKeyState] = true;
					queue.offer(new Node(nx, ny, newKeyState, current.dist + 1));
				}
			}
		}
		System.out.println(-1);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Map = new char[N][M];
		visited = new boolean[N][M][64];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				Map[i][j] = line.charAt(j);
				if (Map[i][j] == '0') {
					startX = i;
					startY = j;
				}
			}
		}
		bfs();
	}
}