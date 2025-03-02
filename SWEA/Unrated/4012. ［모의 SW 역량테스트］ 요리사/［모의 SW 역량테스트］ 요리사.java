/*
 *조합으로 팀 A B에 나눠서 최소 값을 얻음
 * */

import java.io.*;
import java.util.*;

public class Solution {
	private static int T, N, MIN;
	private static int[][] map;
	private static int[] input; // 원소들을 저장할 배열
	private static boolean[] selected;
	private static void Combination(int cnt, int start) {
        if (cnt == N / 2) { // A팀 절반을 선택하면 B팀이 자동 결정됨
            calcSynergy();
            return;
        }

        for (int i = start; i < N; i++) {
            selected[i] = true; // A팀으로 선택
            Combination(cnt + 1, i + 1);
            selected[i] = false;
        }
    }

    private static void calcSynergy() {
        int aSynergy = 0, bSynergy = 0;
        List<Integer> teamA = new ArrayList<>();
        List<Integer> teamB = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (selected[i]) teamA.add(i);
            else teamB.add(i);
        }

        // 팀 내에서 두 명씩 뽑아 시너지 계산
        for (int i = 0; i < N / 2; i++) {
            for (int j = i + 1; j < N / 2; j++) {
                int a1 = teamA.get(i), a2 = teamA.get(j);
                int b1 = teamB.get(i), b2 = teamB.get(j);

                aSynergy += map[a1][a2] + map[a2][a1];
                bSynergy += map[b1][b2] + map[b2][b1];
            }
        }

        // 최소 시너지 차이 갱신
        MIN = Math.min(MIN, Math.abs(aSynergy - bSynergy));
    }


	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			input = new int[N];
			MIN = Integer.MAX_VALUE;
			selected = new boolean[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				input[i] = i + 1;
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			Combination(0, 0);
			sb.append("#").append(tc).append(" ").append(MIN).append("\n");
		}
		System.out.println(sb);
	}
}