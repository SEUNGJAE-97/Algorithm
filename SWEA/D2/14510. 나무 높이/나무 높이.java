import java.io.*;
import java.util.*;

public class Solution {
	private static int T, N, dest, even, odd;
	private static List<Integer> trees;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			String[] split = br.readLine().split(" ");
			trees = new ArrayList<>();
			int even = 0, odd = 0;
			
			for (int i = 0; i < N; i++) {
				trees.add(Integer.parseInt(split[i]));
			}

			dest = Collections.max(trees);
			for (int i = 0; i < N; i++) {
				int diff = dest - trees.get(i);

				if (diff == 0) continue;

				even += diff / 2;
				odd += diff % 2;
			}
			
			if(even > odd) {
				while(Math.abs(even-odd)>1) {
					even--;
					odd +=2;
				}
			}
			int result = 0;
			if(odd > even) result = odd * 2 -1; 	// 1의 개수가 2의 개수보다 많은 경우
			else if(even > odd) result = even * 2; 	// 1의 개수가 2의 개수보다 적은 경우
			else result = odd + even; // 1과 2의 개수가 같은 경우
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}