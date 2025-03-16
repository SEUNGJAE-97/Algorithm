import java.io.*;
import java.util.*;

public class Main {
	private static int N, Max_Num = 0;
	private static int[] S;
	private static boolean[] numbers;
	private static boolean[] visited;

	private static void subset(int cnt) {
		if (cnt == N) {
			int num = 0;
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					num += S[i];
				}
			}
			numbers[num] = true;
			return;
		}
		visited[cnt] = true;
		subset(cnt + 1);
		visited[cnt] = false;
		subset(cnt + 1);

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		S = new int[N];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
			Max_Num += S[i];
		}
		numbers = new boolean[Max_Num + 1];
		numbers[0] = true;
		subset(0);

		for (int i = 0; i < numbers.length; i++) {
			if (!numbers[i]) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(numbers.length);
	}
}