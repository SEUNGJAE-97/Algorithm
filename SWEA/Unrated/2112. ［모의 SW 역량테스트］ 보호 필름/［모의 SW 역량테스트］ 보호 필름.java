import java.io.*;
import java.util.*;

public class Solution {

	// 부분집합으로 0~2개를 구한다.
	// A로 하거나 B로 하거나 두개의 가능성에 대해서 함수를 두번 체크
	// 각각의 셀이 합격인지 확인
	// 가지치기 : 약품투입 횟수가 2를 초과하는 경우
	// 체크과정에서 통과못하면 바로 탈출
	// 0에서 통과하면 바로 탈출
	// 이전보다 많은 약물사용시 탈출

	private static int T, D, W, K;
    private static int[][] map;
    private static int[][] copy;
    private static int min;

    private static boolean check(int col) {
        int maxCnt = 1, cnt = 1;
        
        for (int i = 1; i < D; i++) {
            if (copy[i][col] == copy[i - 1][col]) {
                cnt++;
                maxCnt = Math.max(maxCnt, cnt);
            } else {
                cnt = 1;
            }
        }
        return maxCnt >= K;
    }

    private static boolean isPass() {
        for (int i = 0; i < W; i++) {
            if (!check(i)) return false;
        }
        return true;
    }

    private static void dfs(int depth, int count) {
        // 가지치기: 현재 최소 약물 사용 횟수를 초과하면 중단
        if (count >= min) return;

        if (isPass()) {
            min = count;
            return;
        }

        if (depth == D) return;

        int[] temp = copy[depth].clone();

        dfs(depth + 1, count);

        Arrays.fill(copy[depth], 0);
        dfs(depth + 1, count + 1);

        Arrays.fill(copy[depth], 1);
        dfs(depth + 1, count + 1);

        copy[depth] = temp;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[D][W];
            copy = new int[D][W];

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 초기 최소 약물 사용 횟수를 D로 설정 (최악의 경우 모든 행을 변경)
            min = D;
            for (int i = 0; i < D; i++) {
                copy[i] = map[i].clone();
            }

            // 백트래킹 탐색 시작
            dfs(0, 0);

            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.print(sb);
    }
}