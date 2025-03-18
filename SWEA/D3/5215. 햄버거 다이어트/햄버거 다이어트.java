import java.io.*;
import java.util.*;

public class Solution {
	private static int T, N, L;
	private static List<Burger> burgers;
	private static boolean[] visited;
	private static int maxScore;

	private static class Burger {
		int score, calorie;

		public Burger(int score, int calorie) {
			super();
			this.score = score;
			this.calorie = calorie;
		}

		@Override
		public String toString() {
			return "Burger [score=" + score + ", calorie=" + calorie + "]";
		}
	}

	private static void subset(int cnt) {
		if (cnt == N) {
			int resultC = 0;
			int resultS = 0;

			for (int i = 0; i < N; i++) {
				if (visited[i] == true) {
					resultC += burgers.get(i).calorie;
					resultS += burgers.get(i).score;
				}
			}
			// 총 칼로리가 제한선보다 높으면
			if (resultC > L)
				return;
			// 총점이 기존 점수보다 높으면 종료
			if (resultS > maxScore)
				maxScore = resultS;
			return;
		}
		visited[cnt] = true;
		subset(cnt + 1);
		visited[cnt] = false;
		subset(cnt + 1);

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			burgers = new ArrayList<>();
			visited = new boolean[N];
			maxScore = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int calorie = Integer.parseInt(st.nextToken());
				burgers.add(new Burger(score, calorie));
			}

			subset(0);

			sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
		}
		System.out.println(sb);
	}

}
