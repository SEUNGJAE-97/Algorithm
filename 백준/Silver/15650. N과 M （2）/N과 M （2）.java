import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] numbers;

	private static void combination(int cnt, int start) {
		if (cnt == M) {
			for (int num : numbers) {
				System.out.print(num);
				System.out.print(" ");
			}
			System.out.println();
			return;
		}
		for (int i = start; i <= N; i++) {
			
			numbers[cnt] = i;
			combination(cnt + 1, i+1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		numbers = new int[M];
		combination(0, 1);

	}
}