import java.io.*;
import java.util.*;

public class Main {
	private static int T, N, dest;
	private static int[] M, dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = new int[N+1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				M[i] = Integer.parseInt(st.nextToken());
			}
			dest = Integer.parseInt(br.readLine());
			
			dp = new int[dest+1];
			dp[0] = 1;
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= dest; j++) {
					if(j - M[i] >= 0) dp[j] = dp[j] + dp[j-M[i]];
				}
			}
			sb.append(dp[dest]).append("\n");
		}
		System.out.println(sb);
	}
}