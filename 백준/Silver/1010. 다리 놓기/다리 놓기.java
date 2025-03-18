import java.io.*;
import java.util.*;

public class Main {
	// 이항계수 문제
	private static int[][] memo;
	private static int T, N, M;
	
	// DP버전
	private static long bino(int n, int k) {

		// 1. 동적 테이블 생성
		int[][] D = new int[k + 1][k + 1]; // nC0 ~ nCn까지 모두 구함
		// 2. 베이스 값 채우기
		// 3. 점화식을 이용하여 상향식으로 동적 테이블 채우기
		for (int i = 0; i <= k; i++) {
			int end = Math.min(i, k);
			for (int j = 0; j <= end; j++) {
				// 베이스 값 채우기
				if (j == 0 || j == i) {
					D[i][j] = 1;
				} else {
					D[i][j] = D[i - 1][j - 1] + D[i - 1][j];
				}
			}
		}
		return D[k][n];
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());			
			
			System.out.println(bino(N,M));
		}
		
	}
}
