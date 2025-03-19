import java.io.*;
import java.util.*;

public class Solution {
	// 서비스 영역 - 1번만큼 bfs를 시도했을때 정확히 면적이 일치함
	// 보안회사의 이익은 0이상이어야함
	// (면적내의 집 개수 * 지불비용) - 운영비용 >= 0
	// 면적을 1씩 늘릴때마다 현재 서비스영역 이익이 0에 가장 가까운 영역이 있는지 확인하고 갱신
	private static int T, N, M, result;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };

	private static class Node {
		int x, y, depth;

		public Node(int x, int y, int depth) {
			super();
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}

	private static void printMatrix() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == true)
					System.out.print("o" + " ");
				else if (visited[i][j] == false)
					System.out.print("x" + " ");
			}
			System.out.println();
		}
	}

	private static int getCost(int K) {
		return K * K + (K - 1) * (K - 1);
	}

	private static void bfs(int x, int y) {
		visited = new boolean[N][N];
		Deque<Node> queue = new ArrayDeque<>();
		visited[x][y] = true;
		queue.offer(new Node(x, y, 1));
		int K = 1;
		int cnt = 0;
		if (map[x][y] == 1)
			cnt = 1;
		int maxHouses = cnt;

		while (!queue.isEmpty()) {
			int size = queue.size();
			// K 크기를 증가
			for (int i = 0; i < size; i++) {
				Node current = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nx = current.x + dx[d];
					int ny = current.y + dy[d];
					if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
						queue.offer(new Node(nx, ny, current.depth + 1));
						visited[nx][ny] = true;
						if (map[nx][ny] == 1)
							cnt++;
					}
				}
			}
			K++;
			int cost = getCost(K);

			// 손해여부확인
			if (cnt * M >= cost) {
				maxHouses = Math.max(maxHouses, cnt);
			}
			// Test
//			printMatrix();
//			System.out.println();
		}
		result = Math.max(result, maxHouses);

	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("S_2117_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			result = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 1인 곳에 대하여 bfs 실행
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j);
				}
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}