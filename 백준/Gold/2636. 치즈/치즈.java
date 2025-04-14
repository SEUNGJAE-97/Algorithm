import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, T, C;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };

	private static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	private static void check() {
		System.out.println("=============================================");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					System.out.print("O");
				} else if (map[i][j] == 2) {
					System.out.print("X");
				} else if (map[i][j] == 0) {
					System.out.print("X");
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	// 4곳중에서 한곳이라도 0이라면 2(한꺼번에 지워야함)로 표시하자
	private static void bfs(int startX, int startY) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(startX, startY));

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			visited[cur.x][cur.y] = true;

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				// nx,ny가 맵안에 들어가는 범위면서 방문을 안했고, 만약 0이라면? 탐색
				if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
					visited[nx][ny] = true;

					if (map[nx][ny] == 0) {
						queue.add(new Node(nx, ny));

					} else if (map[nx][ny] == 1) {
						map[nx][ny] = 2;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int temp = 0;
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			visited = new boolean[N][M];
			bfs(0, 0);
			int count = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1 || map[i][j] == 2) {
						count++;
					}
				}
			}
			if (count == 0) {
				// 다 녹았으면 종료
				break;
			}

			temp = count;

			// 치즈 지우기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 2) {
						map[i][j] = 0;
					}
				}
			}
			// 시간 증가
			T++;

		}
		System.out.println(T);
		System.out.println(temp);
	}
}