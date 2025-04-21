import java.io.*;
import java.util.*;

public class Solution {
	private static int T, W, H, N, Result;
	private static int[][] map;
	private static int[] selected;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };

	private static void permutation(int cnt) {
		if (cnt == N) {
//			System.out.println(Arrays.toString(selected));
			int remain = simulation(selected);
			Result = Math.min(Result, remain);
			return;
		}

		for (int i = 0; i < W; i++) {
			selected[cnt] = i;
			permutation(cnt + 1);
		}
	}

	private static int simulation(int[] selected) {
		// map 복사
		int[][] copyMap = new int[H][W];

		for (int i = 0; i < H; i++) {
			System.arraycopy(map[i], 0, copyMap[i], 0, W);
		}

		for (int i = 0; i < N; i++) {
			int col = selected[i];
			int row = -1;

			for (int r = 0; r < H; r++) {
				if (copyMap[r][col] > 0) {
					row = r;
					break;
				}
			}

			if (row == -1)
				continue;

			Queue<int[]> queue = new ArrayDeque<>();
			queue.offer(new int[] { row, col, copyMap[row][col] });
			copyMap[row][col] = 0;

			while (!queue.isEmpty()) {
				int[] cur = queue.poll();
				int cr = cur[0], cc = cur[1], power = cur[2];
				for (int d = 0; d < 4; d++) {
					for (int dist = 1; dist < power; dist++) {
						int nr = cr + dx[d] * dist;
						int nc = cc + dy[d] * dist;
						if (nr < 0 || nr >= H || nc < 0 || nc >= W)
							continue;
						if (copyMap[nr][nc] > 0) {
							if (copyMap[nr][nc] > 1) {
								queue.offer(new int[] { nr, nc, copyMap[nr][nc] });
							}
							copyMap[nr][nc] = 0;
						}
					}
				}
			}
			// 4. 중력 처리
			for (int c = 0; c < W; c++) {
				int[] temp = new int[H];
				int idx = H - 1;
				for (int r = H - 1; r >= 0; r--) {
					if (copyMap[r][c] > 0) {
						temp[idx--] = copyMap[r][c];
					}
				}
				for (int r = H - 1; r >= 0; r--) {
					copyMap[r][c] = temp[r];
				}
			}
		}

		// 5. 남은 벽돌 개수 세기
		int count = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (copyMap[i][j] > 0)
					count++;
			}
		}
		return count;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			selected = new int[N];
			Result = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			permutation(0);
			
			sb.append("#").append(tc).append(" ").append(Result).append("\n");
		}
		System.out.println(sb);
	}
}