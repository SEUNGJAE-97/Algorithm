import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] numbers;
	private static List<Integer> input;
	private static boolean[] visited;

	private static void permutation(int cnt) {
		if (cnt == N) {
			// 출력
			for (int i = 0; i < N; i++) {
				System.out.print(numbers[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				numbers[cnt] = input.get(i);
				permutation(cnt + 1);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		input = new ArrayList<>();
		numbers = new int[N];
		visited = new boolean[N];
		// input 데이터 추가
		for (int i = 1; i <= N; i++) {
			input.add(i);
		}

		permutation(0);
	}
}