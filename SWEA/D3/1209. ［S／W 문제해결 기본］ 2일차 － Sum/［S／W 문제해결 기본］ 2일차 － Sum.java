import java.util.*;
import java.io.*;

public class Solution {
	private static int SquareCal(int square[][]) {
		int ColMax = Integer.MIN_VALUE;
		int RowMax = Integer.MIN_VALUE;
		int[] result = new int[2];
		for (int i = 0; i < square.length; i++) {
			int colsum = 0;
			int rowsum = 0;
			for (int j = 0; j < square.length; j++) {
				colsum += square[i][j];
				rowsum += square[j][i];
			}
			if (ColMax < colsum) {
				ColMax = colsum;
			}
			if (RowMax < rowsum) {
				RowMax = rowsum;
			}
		}
		result[0] = RowMax;
		result[1] = ColMax;

		return Math.max(result[0], result[1]);
	}

	private static int diagonalCal(int square[][]) {
		int[] result = new int[2];
		for (int i = 0; i < square.length; i++) {
			result[0] += square[i][i];
			result[1] += square[i][square.length - i - 1];
		}
		return Math.max(result[0], result[1]);
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int TestCase = 1; TestCase <= 10; TestCase++) {
			sb.append("#").append(TestCase).append(" ");
			br.readLine();
			int[][] array = new int[100][100];
			for (int i = 0; i < 100; i++) {
				String[] line = br.readLine().split(" ");
				for (int j = 0; j < 100; j++) {
					array[i][j] = Integer.parseInt(line[j]);
				}
			}

			int garosaeroMax = SquareCal(array);
			int DiagonalMax = diagonalCal(array);
			sb.append(Math.max(garosaeroMax, DiagonalMax)).append("\n");
		}
		System.out.println(sb);
	}
}