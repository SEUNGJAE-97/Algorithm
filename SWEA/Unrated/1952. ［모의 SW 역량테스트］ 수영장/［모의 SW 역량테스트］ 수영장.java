import java.io.*;
import java.util.*;

public class Solution {
	private static int T, Day, One_Month, Three_Month, Year, Result;
	private static int[] Calendar = new int[13];

	private static void dfs(int month, int cost) {
		if (month > 12) {

			Result = Math.min(Result, cost);
			return;

		}

		if (Calendar[month] > 0) {
			dfs(month + 1, cost + Day * Calendar[month]);
			dfs(month + 1, cost + One_Month);
			dfs(month + 3, cost + Three_Month);
			
		} else {
			dfs(month + 1, cost);
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			Day = Integer.parseInt(st.nextToken());
			One_Month = Integer.parseInt(st.nextToken());
			Three_Month = Integer.parseInt(st.nextToken());
			Year = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				Calendar[i] = Integer.parseInt(st.nextToken());
			}

			Result = Year;

			dfs(1, 0);
			sb.append("#").append(tc).append(" ").append(Result).append("\n");

		}
		System.out.println(sb);
	}
}