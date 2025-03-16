import java.io.*;
import java.util.*;

public class Main {
	private static int N, S, Count;
	private static int[] input;
	private static boolean[] isSelected;

	private static void subset(int cnt) {
		if (cnt == N) {
			int result = 0;
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					result += input[i];
				}
			}
			if (result == S) {
				Count++;
			}
			return;
		}

		isSelected[cnt] = true;
		subset(cnt + 1);
		isSelected[cnt] = false;
		subset(cnt + 1);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		input = new int[N];
		isSelected = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		subset(0);

		System.out.println(S == 0 ? Count - 1 : Count);
	}
}