import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

public class Main {
	private static int N, towns = 0;
	private static Deque<Node> stack;
	private static int[][] map;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static ArrayList<Integer> list = new ArrayList<>();

	public static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static void DFS(int startX, int startY) {
		stack.offer(new Node(startX, startY));
		map[startX][startY] = 0;
		int cnt = 1;
		while (!stack.isEmpty()) {
			Node current = stack.poll();
			// 4방향 탐색
			for (int d = 0; d < 4; d++) {

				int nx = dx[d] + current.x;
				int ny = dy[d] + current.y;
				// 맵 안에 존재하면서 1인 곳이면 탐색
				if (nx >= 0 && ny >= 0 && nx < N && ny < N && map[nx][ny] == 1) {
					map[nx][ny] = 0;
					stack.offer(new Node(nx, ny));
					cnt++;
				}
			}
		}
		list.add(cnt);
		towns++;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // N 크기를 입력받음
		stack = new ArrayDeque<>();
		map = new int[N][N];

		// N x N 배열 입력 받기
		for (int i = 0; i < N; i++) {
			String split = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = split.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1)
					DFS(i, j);
			}
		}
		Collections.sort(list);

		System.out.println(towns);
		for (int i : list) {
			System.out.println(i);
		}
	}
}