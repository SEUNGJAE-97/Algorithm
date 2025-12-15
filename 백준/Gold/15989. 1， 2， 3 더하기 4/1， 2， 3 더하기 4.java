import java.util.*;
import java.io.*;

public class Main {

	private static int N, T;
	private static int[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		dp = new int[4][10001]; 
		dp[0][0] = 1;
		 for (int j = 1; j <= 3; j++) {
	            for (int sum = 0; sum <= 10000; sum++) {
	                dp[j][sum] = dp[j - 1][sum];

	                if (sum - j >= 0) {
	                    dp[j][sum] += dp[j][sum - j];
	                }
	            }
	        }
		 
		 
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			//sol 
			sb.append(dp[3][N]).append('\n');
			
		}
		System.out.println(sb);

	}

}
