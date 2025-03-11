import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Main {
	private static int minResult;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		if (N % 5 == 0) {
			System.out.println(N / 5);
		} else {
			minResult = 0;
			while (N > 0) {
				N -= 3;
				minResult++;
				// 1. 3으로 나누어 떨어지는지 확인
				if (N % 5 == 0) {
					minResult += N / 5;
					System.out.println(minResult);
					break;
				} else if (N == 1 || N == 2) {
					System.out.println(-1);
					break;
				} else if (N == 0) {
					System.out.println(minResult);
					break;
				}
			}
		}
	}
}