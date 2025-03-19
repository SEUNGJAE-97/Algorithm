import java.io.*;
import java.util.*;

public class Main {
	private static int N, K;
	private static int[] weights, values;
	private static int[][] D;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		weights = new int[N+1];
		values = new int[N+1];
		D = new int[N+1][K+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			weights[i] = Integer.parseInt(st.nextToken());
			values[i] = Integer.parseInt(st.nextToken());
		}
		for (int item = 1; item <= N; item++) {
			int itemWeight = weights[item]; 
			int itemProfit = values[item];
			for(int weight = 1; weight<=K; weight++) {
				// 아이템을 담을 수 있는 경우
				if (itemWeight <= weight) {
					D[item][weight] = Math.max(D[item - 1][weight], D[item - 1][weight - itemWeight] + itemProfit);
				}
				// 아이템을 임시배낭에 담을 수 없는 경우
				else {
					D[item][weight] = D[item - 1][weight];
				}
			}
		}
		System.out.println(D[N][K]);
	}
}
