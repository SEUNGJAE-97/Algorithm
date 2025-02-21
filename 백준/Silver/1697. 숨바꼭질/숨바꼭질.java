import java.util.*;
import java.io.*;

public class Main {
	private static int S; // 초
	private static int C; // 현재 위치
	private static boolean[] V = new boolean[100001]; // 방문체크
	private static Queue<Integer> queue = new ArrayDeque<>();

	private static void SUMBAKKOGJIL(int start, int end) {
		V[start] = true;
		C = 0;
		S = 0;
		queue.offer(start);
		while (V[end] != true) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				C = queue.poll();
				
				if ( C - 1 >= 0 && V[C - 1] == false) {
					V[C - 1] = true;
					queue.offer(C - 1);
				}
				if (C + 1 <= 100000 && V[C + 1] == false) {
					V[C + 1] = true;
					queue.offer(C + 1);
				}
				if (C * 2 <= 100000 && V[C * 2] == false) {
					V[C * 2] = true;
					queue.offer(C * 2);
				}
			}

			//System.out.println(queue);
			S++;
		}
		System.out.println(S);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] arr = br.readLine().split(" ");

		SUMBAKKOGJIL(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));

	}
}