import java.io.*;

public class Main {
	private static String N;
	private static int[] numbers;
	private static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		numbers = new int[10];
		max = Integer.MIN_VALUE;
		N = br.readLine();

		for (int i = 0; i < N.length(); i++) {
			char num = N.charAt(i);
			if (num == '6' || num == '9') {
				numbers[9]++;
			} else {
				numbers[Character.getNumericValue(num)]++;
			}
		}
		numbers[9] = (numbers[9] % 2) + (numbers[9] / 2);
		for (int j : numbers) {
			if (j > max)
				max = j;
		}
		System.out.println(max);
	}

}