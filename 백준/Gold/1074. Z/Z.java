import java.io.*;
import java.util.*;

public class Main {
	private static int r, c, result;

	private static void solution(int one_direction, int x, int y, int N) {
		int direction = 0;
		int n  = (int) Math.pow(2, 2 * (N-1));
		// 종료조건
		if(N == 1) {
			if (y > 1) y = 1;
			else if(x > 1) x = 1;
			
			int[][] list = new int[2][2];
			for(int i = 0; i < 2; i ++) {
				for(int j = 0; j<2; j++) {
					list[i][j] = one_direction++;
				}
			}
			result = list[x][y];
			return;
		}
		
		// 몇사분면에 위치하는지 확인
		if(x >= Math.pow(2, (N-1)) && y >= Math.pow(2, (N-1))) direction = 3;
		else if(x >= Math.pow(2, (N-1)) && y < Math.pow(2, (N-1))) direction = 2;
		else if(x < Math.pow(2, (N-1)) && y >= Math.pow(2, (N-1))) direction = 1;
		else if(x < Math.pow(2, (N-1)) && y < Math.pow(2, (N-1))) direction = 0;
		// 해당 사분면의 1사분면의 숫자 + 단위(2^n * n)가 내가 찾는 
		// 단위  = 2^n -1 * 2^N-1
		if(Math.pow(2, N-1) <= x) {
			x -= Math.pow(2, N-1);
		}
		if(Math.pow(2, N-1) <= y) {
			y -= Math.pow(2, N-1);
		}
		solution(one_direction + n * direction, x, y, N-1);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		solution(0, r, c, N);
		System.out.println(result);

	}

}