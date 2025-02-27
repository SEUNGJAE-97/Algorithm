import java.util.*;
import java.io.*;

public class Solution {
	private static int T, N, K;
	private static int[][] MAP;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int MAX;
	private static boolean[][] V;

	private static void dfs(int x, int y, int length, boolean used) {
		MAX = Math.max(MAX, length);

		for (int i = 0; i < 4; i++) {
			int newx = x + dx[i];
			int newy = y + dy[i];

			// 맵을 이탈하는 경우
			if (newx < 0 || newy < 0 || newx >= N || newy >= N)
				continue;
			if (V[newx][newy])
				continue;

			// 그냥 내려가는 경우
			if (MAP[newx][newy] < MAP[x][y]) {
				V[newx][newy] = true;
				dfs(newx, newy, length + 1, used); // DFS 재귀 호출
				V[newx][newy] = false;
			}
			// k만큼 깎아서 갈수있다면?
			else if (!used && MAP[newx][newy] - K < MAP[x][y]) {
				int original = MAP[newx][newy]; // 원래 높이 저장
				
				// 1~K만큼 깎는다. 
				MAP[newx][newy] = Math.max(MAP[x][y] - 1, MAP[newx][newy]-K); 
				V[newx][newy] = true;
				// used = true로 변경 후 DFS
				dfs(newx, newy, length + 1, true);

				V[newx][newy] = false;
				MAP[newx][newy] = original; // 원래 높이 복구
			}
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			MAX = Integer.MIN_VALUE;
			V = new boolean[N][N];
			MAP = new int[N][N];
			int highest = 0;

			for (int i = 0; i < N; i++) {
				String split[] = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					MAP[i][j] = Integer.parseInt(split[j]);
					highest = Math.max(highest, MAP[i][j]);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (MAP[i][j] == highest) {
						V[i][j] = true;
						dfs(i, j, 1, false);
						V[i][j] = false;
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(MAX).append("\n");
		}
		System.out.println(sb);
	}
}