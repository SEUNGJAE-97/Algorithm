// 한줄마다 조합으로 경우의 수를 뽑고 가장 큰것을 가져온다?

import java.io.*;
import java.util.*;

public class Solution {
    private static int N, M, C, maxProfit;
    private static int[][] map, profit;

    private static void calculateProfit(int x, int y) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            list.add(map[x][y + i]);
        }

        int maxP = 0;
        int size = list.size();
        for (int subset = 0; subset < (1 << size); subset++) {
            int sum = 0, p = 0;
            for (int i = 0; i < size; i++) {
                if ((subset & (1 << i)) > 0) {
                    sum += list.get(i);
                    p += list.get(i) * list.get(i);
                }
            }
            if (sum <= C) maxP = Math.max(maxP, p);
        }
        profit[x][y] = maxP;
    }

    private static void findMaxProfit() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= N - M; j++) {
                for (int i2 = i; i2 < N; i2++) {
                    for (int j2 = (i == i2) ? j + M : 0; j2 <= N - M; j2++) {
                        maxProfit = Math.max(maxProfit, profit[i][j] + profit[i2][j2]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
       // System.setIn(new FileInputStream("sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            profit = new int[N][N];
            maxProfit = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 각 벌통 위치에서 최적의 이익 계산
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) {
                    calculateProfit(i, j);
                }
            }

            // 두 일꾼이 선택한 벌통이 겹치지 않도록 하면서 최대 이익 찾기
            findMaxProfit();
            sb.append("#").append(tc).append(" ").append(maxProfit).append("\n");
        }
        System.out.println(sb);
    }
}