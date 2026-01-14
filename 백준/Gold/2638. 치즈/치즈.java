import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[][] matrix;
	static boolean[][] visited;
	static ArrayList<Node> cheeses;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	private static class Node{
		int x ,y;
		private Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	private static void bfs() {
		Queue<Node> deque = new ArrayDeque<Node>();
		deque.offer(new Node(0,0));
		visited[0][0] = true;
		matrix[0][0] = 2;
		
		while(!deque.isEmpty()) {
			Node cur = deque.poll();
			int x = cur.x;
			int y = cur.y;
			
			for(int d = 0; d< 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if (visited[nx][ny] || matrix[nx][ny] == 1) continue; 
				
				matrix[nx][ny] = 2;
				deque.offer(new Node(nx, ny));
				visited[nx][ny] = true;
			}
		}
	}
	
	private static void solution() {
		for(int i = 0; i<cheeses.size(); i++ ) {
			int x = cheeses.get(i).x;
			int y = cheeses.get(i).y;
			int cnt = 0;
			for(int j = 0; j < 4; j++) {
				int nx = x + dx[j];
				int ny = y + dy[j];
				if(matrix[nx][ny] == 2) cnt++;
				
			}
			if(cnt >= 2) {
				matrix[x][y] = 0;
				cheeses.remove(i);
				i--;
			}
			
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		matrix = new int[N][M];
		cheeses = new ArrayList<Node>();
		
		for(int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				if(matrix[i][j] == 1) {
					cheeses.add(new Node(i, j));
				}
			}
		}
		int time = 0;
		while(!cheeses.isEmpty()) {
			time++;
			visited = new boolean[N][M];
			bfs();
			solution();
		}
		
		System.out.println(time);
	}

}
