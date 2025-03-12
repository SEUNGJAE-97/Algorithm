import java.io.*;
import java.util.*;

public class Main {
	static int r, c, cnt = 0;
	static char[][] map;
	static boolean[][] check;
	static int[] dr = { -1, 0, 1 }; // 출발점 0~r-1이므로, 도착점 0~r-1순대로 도착하게 탐색 방향 설정

	private static boolean dfs(int x, int y) {
		
		for(int d = 0; d < 3; d ++) {
			int nx = x + 1;
			int ny = y + dr[d];
			
			if(nx < 0 || ny <0 || nx > c-1 || ny > r-1) continue;
			// 만약 갈수있다면 
			if(map[ny][nx] =='.') {
				// 종료조건 확인
				if(nx == c-1) {
					cnt++;
					return true;
				}
				// 종료하지 않는다면 방문처리 
				map[ny][nx] = '-';
				if(dfs(nx,ny)) return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];

		for (int i = 0; i < r; i++) {
			String line = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		// 0~r-1순으로 출발점 설정
		for (int i = 0; i < r; i++) {
			dfs(0, i);
		}
		System.out.println(cnt);
	}
}