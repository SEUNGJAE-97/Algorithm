import java.io.*;
import java.util.*;

public class Solution {
	private static int N, X, T, result;
	private static int[][] map;
	private static List<List<Integer>> garo;
	private static List<List<Integer>> sero;
	
	private static void gongsa(List<List<Integer>> list) {
		for(List<Integer> target : list) {
			int cnt = 1;
			boolean check = true;
			
			for(int i = 0; i < N-1; i++) {
				// 두개의 차이가 1보다 크면 안되는 활주로 
				if(Math.abs(target.get(i)-target.get(i+1))>1) {
					check = false;
					break; 
				}
				// 같으면 카운트 시작 
				if(Math.abs(target.get(i)-target.get(i+1)) == 0) {
					cnt++;
				}
				// 높이가 -1 차이나면 경사로  
				else if(target.get(i)-target.get(i+1) == -1) {
					if(cnt < X) {
						check = false;
						break;
					}
					cnt = 1;
				}
				// 내려가는 코드
				else if(target.get(i)-target.get(i+1) == 1) {
					cnt =1;
					// i+1부터 X만큼 확보 가능한지 .
					for(int j = i+1; j<i+X+1;j++) {
						// 경사로 설치했을때 맵을 나가는지, 현재 위치와 다음위치가 다른경우 설치불가
						if(j >= N || target.get(j) != target.get(i+1)) {
							check = false;
							break;
						}
						cnt++;
					}
					// 만일 false라면 탈출 
					if(cnt < X) {
						check = false;
						break;
					}
					i += (X-1);
					cnt = 0;
				}
				
			}
			if(check)result++;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			garo = new ArrayList<>();
			sero = new ArrayList<>();
			map = new int[N][N];
			result = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < N; i++) {
				List<Integer> g = new ArrayList<>();
				List<Integer> s = new ArrayList<>();
				for(int j = 0; j<N; j++) {
					g.add(map[i][j]);
					s.add(map[j][i]);
				}
				garo.add(g);
				sero.add(s);
			}			
			gongsa(garo);
			gongsa(sero);
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);

	}
}