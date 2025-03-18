import java.io.*;
import java.util.*;

public class Main {
	private static int N, cnt;
	private static int[][] map;
	// 방향별 이동 정의: 가로(0), 세로(1), 대각선(2)
	private static int[][] move = { { 0, 1 }, { 1, 0 }, { 1, 1 } };

	private static void dfs(int x, int y, int dir) {
		if (x == N - 1 && y == N - 1) {
			cnt++;
			return;
		}

		for (int d = 0; d < 3; d++) {
			// 가로 → 세로, 세로 → 가로 불가능
			if ((dir == 0 && d == 1) || (dir == 1 && d == 0))
				continue; 

			int nx = x + move[d][0];
			int ny = y + move[d][1];

			if (nx < N && ny < N && map[nx][ny] == 0) {
				if (d == 2) { // 대각선 이동 시 추가 체크
					if (map[x + 1][y] == 1 || map[x][y + 1] == 1)
						continue;
				}
				dfs(nx, ny, d);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 1, 0);
		System.out.println(cnt);
	}
}