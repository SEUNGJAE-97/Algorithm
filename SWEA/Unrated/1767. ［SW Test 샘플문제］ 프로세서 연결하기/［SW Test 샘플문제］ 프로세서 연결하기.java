import java.util.*;
import java.io.*;

public class Solution {
	private static int T, N, MaxCore, MinWire;
	private static int[][] map;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static List<int[]> cores;

	private static void dfs(int idx, int connected, int wire) {
		if(idx == cores.size()) {
			if(connected > MaxCore) {
				MaxCore = connected;
				MinWire = wire;
			}else if(connected == MaxCore) {
				MinWire = Math.min(MinWire, wire);
			}
			return;
		}
		
		int[] core = cores.get(idx);
		int x = core[0];
		int y = core[1];
		
		// 4방면 탐색  
		for(int d = 0; d<  4; d++) {
			int nx = x;
			int ny = y;
			int len = 0;
			
			// 전선 설치 가능여부 
			while(true) {
				nx += dx[d];
				ny += dy[d];
				
				if(nx < 0 || ny < 0 || nx >=N || ny >= N) break;
				// 0이 아니라면 설치불가 
				if(map[nx][ny] != 0) {
					len = 0;
					break;
				}
				len ++;
			}
			
			// 전선 설치  
			if(len > 0) {
				nx = x;
				ny = y;
				
				for(int i = 0; i < len; i++) {
					nx += dx[d];
					ny += dy[d];
					map[nx][ny] = 2;
				}
				
				dfs(idx + 1, connected + 1, wire + len);
				
				nx = x;
				ny = y;
				for(int i = 0; i< len; i++) {
					nx += dx[d];
					ny += dy[d];
					map[nx][ny] = 0;
				}
			}
		}
		// 연결 안하는 경우
		dfs(idx + 1, connected, wire);
	}
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			cores = new ArrayList<>();
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				String[] split = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(split[j]);

					// 1. 외곽에 있는 코어는 블럭처리
					if (map[i][j] == 1 && !(i == 0 || i == N - 1 || j == 0 || j == N - 1)) {
						cores.add(new int[] { i, j });
					}
				}
			}
			
			MaxCore = 0;
			MinWire = Integer.MAX_VALUE;

			// 2. dfs
			dfs(0,0,0);
			
			sb.append("#").append(tc).append(" ").append(MinWire).append("\n");
		}
		System.out.println(sb);
	}
}