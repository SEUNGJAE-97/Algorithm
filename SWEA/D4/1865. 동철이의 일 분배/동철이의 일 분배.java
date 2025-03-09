import java.io.*;
import java.util.*;

public class Solution {
	private static int T, N;
	private static double maxResult;
	private static double[][] map;
	private static boolean[] visited;

	private static void solution(int person, double result) {
		if (result <= maxResult) {
			return;
		}
		if (person == N) {
			maxResult = result;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				solution(person + 1, result * map[person][i]);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("1865_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			maxResult = 0;
			N = Integer.parseInt(br.readLine());
			map = new double[N][N];
			visited = new boolean[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Double.parseDouble(st.nextToken()) / 100.0;
				}
			}

			solution(0, 1.0);

			sb.append("#").append(tc).append(" ").append(String.format("%.6f", maxResult * 100)).append("\n");
		}
		System.out.println(sb);
	}
}