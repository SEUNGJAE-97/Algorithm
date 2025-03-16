import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		char[] T = in.readLine().toCharArray(); // 본문
		char[] P = in.readLine().toCharArray(); // 패턴

		int tLength = T.length;
		int pLength = P.length;

		// 1단계: 부분일치 테이블 만들기
		int[] next = new int[pLength];

		for (int i = 1, j = 0; i < pLength; i++) {
			while (j > 0 && P[i] != P[j]) {
				j = next[j - 1];
			}
			if (P[i] == P[j]) {
				next[i] = ++j;
			}
		}

		// 2단계: KMP 탐색
		int cnt = 0;
		List<Integer> list = new ArrayList<>();

		for (int i = 0, j = 0; i < tLength; i++) {
			while (j > 0 && T[i] != P[j]) {
				j = next[j - 1];
			}

			if (T[i] == P[j]) { // 두 글자 일치
				if (j == pLength - 1) { // 패턴 끝까지 매칭된 경우
					cnt++;
					list.add(i - (pLength - 1) + 1); // 1-based index 저장
					j = next[j]; // **패턴 찾았으면 j 갱신**
				} else {
					j++;
				}
			}
		}

		// 출력
		System.out.println(cnt);
		for (int num : list) {
			System.out.print(num + " ");
		}
	}
}