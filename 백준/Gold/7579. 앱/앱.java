import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, sum;
	private static int[] app, cost;
	private static int[]D;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		app = new int[N + 1];
		cost = new int[N + 1];
		

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			app[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			sum += cost[i];
		}

		D = new int[sum + 1];
		
		for (int i = 1; i <= N; i++) {
//			for (int j = 0; j <= sum; j++) {
//				if (j - cost[i] >= 0) D[i][j] = Math.max(D[i][j], D[i - 1][j - cost[i]] + app[i]);		
//				D[i][j] = Math.max(D[i][j], D[i-1][j]);
//			}
			for (int j = sum; j >= cost[i]; j--) {
                D[j] = Math.max(D[j], D[j - cost[i]] + app[i]);
            }
		}
		
		for(int i = 0; i <= sum; i++) {
			if(D[i]>=M) {
				System.out.println(i);
				break;
			}
		}
	}
}
