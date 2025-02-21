import java.util.*;
import java.io.*;

public class Solution {
	private static Queue<Integer> AMHO = new ArrayDeque<>();
	private static int K;
	private static boolean flag;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();
			String[] split = br.readLine().split(" ");
			AMHO.clear();
			K = 0;
			flag = false;
			for (int i = 0; i < 8; i++) {
				AMHO.offer(Integer.parseInt(split[i]));
			}

			// 구현
			while (!flag) {
				for (int i = 1; i <= 5; i++) {
					K = AMHO.poll();
					K -= i;
					if (K <= 0) {
						AMHO.offer(0);
						flag = true;
						break;
					} else {
						AMHO.offer(K);
					}
				}
			}
			// 출력 포맷 수정
            sb.append("#").append(tc).append(" ");
            for (int num : AMHO) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
		}
		System.out.println(sb);
	}
}