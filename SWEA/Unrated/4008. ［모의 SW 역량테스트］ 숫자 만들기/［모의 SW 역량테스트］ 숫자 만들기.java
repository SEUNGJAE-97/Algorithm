import java.io.*;
import java.util.*;

public class Solution {
	private static int T, N;
	private static int[] op;
	private static int[] num;
	private static int MIN, MAX;
	
	private static void permutation(int idx, int result) {
		if (idx == N) { // 모든 숫자 사용 완료
            MIN = Math.min(MIN, result);
            MAX = Math.max(MAX, result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] > 0) { 
                op[i]--; 
                
                int nextResult = result;
                if (i == 0) nextResult += num[idx];  // +
                else if (i == 1) nextResult -= num[idx];  // -
                else if (i == 2) nextResult *= num[idx];  // *
                else if (i == 3) nextResult /= num[idx];  // /
                
                permutation(idx + 1, nextResult);  
                
                op[i]++;  
            }
        }
    }
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			MIN = Integer.MAX_VALUE;
			MAX = Integer.MIN_VALUE;
			N = Integer.parseInt(st.nextToken());

			op = new int[4];
			num = new int[N];
			
			String[] split = br.readLine().split(" ");
            for (int i = 0; i < 4; i++) {
                op[i] = Integer.parseInt(split[i]);
            }

            split = br.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                num[i] = Integer.parseInt(split[i]);
            }
            
			permutation(1, num[0]);

			sb.append("#").append(tc).append(" ").append(MAX-MIN).append("\n");
		}
		System.out.println(sb);
	}
}