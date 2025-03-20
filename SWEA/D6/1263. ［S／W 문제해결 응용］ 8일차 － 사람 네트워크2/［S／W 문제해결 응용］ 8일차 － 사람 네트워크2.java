import java.io.*;
import java.util.*;

public class Solution {
	private static int[][] D;
	private static int N, T;
	private static final int INF = 99999;
	private static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = new int[N][N];
			result = INF;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					D[i][j] = Integer.parseInt(st.nextToken());
					if(i != j && D[i][j]==0) D[i][j] = INF;
				}
			}
			
			// 경유지 출발지 도착지
			for(int k = 0; k < N; k++) {
				for(int i = 0; i < N; i++) {
					if(i==k) continue;
					for(int j = 0; j < N; j++) {
						if(k == j || i == j) continue;
						D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
					}
				}
			}
			for(int i = 0; i < N; i ++) {
				int temp = 0;
				for(int j = 0; j < N; j++) {
					temp +=D[i][j];
				}
				result = Math.min(result, temp);
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}
