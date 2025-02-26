import java.io.*;
import java.util.*;

public class Solution {
	private static int N, M, T;
	private static int[] parents;

	// 단위 집합 생성
	private static void makeSet() {
		// 인덱스 0번부터 사용
		parents = new int[N+1];

		// 자신의 부모정점을 자신의 값으로 설정
		for (int i = 0; i <=N; i++) {
			parents[i] = i;
		}
	}

	// 파라메터 a의 집합 찾기 (a의 대표자 찾기)
	private static int findSet(int a) {
		// a의 부모를 물어보고 만일 자기자신이라면 돌아감
		if (parents[a] == a) {
			return a;
		}

		// return findSet(parents[a]);
		// fath compression
		return parents[a] = findSet(parents[a]);
	}

	// 파라메터 a,b가 속한 집합을 합치기
	private static boolean union(int a, int b) {
		// 각각 원소의 부모를 찾음
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		// 서로 같은 부모를 가지고 있다면, 합쳐지지 않으므로 false를 리턴
		if (aRoot == bRoot) {
			return false;
		}
		// a의 자식으로 b가 들어가게됨
		parents[bRoot] = aRoot;
		// 합쳐졌으므로 true를 리턴
		return true;
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			makeSet();

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (op == 0) {
                    union(a, b);
                } else if (op == 1) {
                    sb.append(findSet(a) == findSet(b) ? 1 : 0);
                }
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}