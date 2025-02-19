import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	private static int N;
	private static boolean[] isSelected; // 각 원소가 부분집합의 구성에 포함되었는지 여부
	private static int[][] FOOD;
	private static int min = Integer.MAX_VALUE;
	private static void JJAPAGURI(int cnt) {
		
		// 기저부분
		if (cnt == N) {
			int S = 1;
			int B = 0;
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					S *= FOOD[i][0];
					B += FOOD[i][1];
				}
				if (Math.abs(S - B) <= min && isSelected[i] == true) {
					min = Math.abs(S - B);
				}
			}
			return;
		}

		// 유도부분
		// 현재 원소를 부분집합 구성에 포함
		isSelected[cnt] = true;
		JJAPAGURI(cnt + 1);

		// 현재 원소를 부분집합 구성에 미포함
		isSelected[cnt] = false;
		JJAPAGURI(cnt + 1);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// init
		N = Integer.parseInt(br.readLine());
		FOOD = new int[N][2];
		isSelected = new boolean[N];

		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < 2; j++) {
				FOOD[i][j] = Integer.parseInt(input[j]);
			}
		}

		// 구현
		JJAPAGURI(0);
		System.out.println(min);
	}

}