import java.io.*;
import java.util.*;

public class Main {
	private static char[][] map;
	private static char[][] map_2;
	private static int N, Result, Result2;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static Deque<Node> stack = new ArrayDeque<>();

	private static class Node {
		int x;
		int y;
		char c;

		public Node(int x, int y, char c) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
		}

	}

	private static void DFS(int startX, int startY, char color, char[][] map) {
		map[startX][startY] = 0;
		stack.offer(new Node(startX, startY, color));

		while (!stack.isEmpty()) {
			Node Current = stack.poll();
			for (int d = 0; d < 4; d++) {
				int nx = dx[d] + Current.x;
				int ny = dy[d] + Current.y;
				char currentColor = Current.c;

				if (nx >= 0 && ny >= 0 && nx < N && ny < N && map[nx][ny] != 0 && currentColor == map[nx][ny]) {
					currentColor = map[nx][ny];
					map[nx][ny] = 0;
					stack.offer(new Node(nx, ny, currentColor));
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		map_2 = new char[N][N];
		Result = 0;
		Result2 = 0;
		
		for (int i = 0; i < N; i++) {
			String split = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = (char) (split.charAt(j));
				if (c == 'R' || c == 'G' && c != 'B') {
					map_2[i][j] = 'R';
				}else {
					map_2[i][j] = c;
				}

				map[i][j] = c;
					
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) {
					DFS(i, j, map[i][j], map);
					Result++;
				}
			}
		}
		
		stack = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map_2[i][j] != 0) {
					DFS(i, j, map_2[i][j], map_2);
					Result2++;
				}
			}
		}
		System.out.println(Result + " " + Result2);
	}
}