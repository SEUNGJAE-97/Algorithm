import java.util.*;
import java.io.*;

public class Main {
	private static int N, M;
	private static int[][] graph;
	private static int[] dx = {0,0,-1,1};
	private static int[] dy = {-1,1,0,0};
	private static Deque<Node> deque;
	private static boolean[][] visited;
	private static class Node{
		int x;
		int y;
		Node(int x, int y) {
			this.x = x; 
			this.y = y; 
		}
	}
	private static int bfs() {
		int day = 0;
		deque = new ArrayDeque<>();
		
		// 익은 토마토넣기 
		for(int i=0; i<M; i++) {
	        for(int j=0; j<N; j++) {
	            if(graph[i][j] == 1) {
	                deque.offer(new Node(i, j));
	                visited[i][j] = true;
	            }
	        }
	    }
		
		while(!deque.isEmpty()) {
			int size = deque.size();
			 for(int s=0; s<size; s++) {
				 Node node = deque.poll();
					for(int d = 0; d < 4; d++) {
						int x = node.x + dx[d];
						int y = node.y + dy[d];
						if(x >= 0 && y >= 0 && x < M && y < N && graph[x][y] == 0) {
							graph[x][y] = 1;
							visited[x][y] = true;
							deque.offer(new Node(x, y));
						}
					}
			 }
			 if(!deque.isEmpty()) day++;
		}
		for(int i=0; i<M; i++) {
	        for(int j=0; j<N; j++) {
	            if(graph[i][j] == 0) return -1;
	        }
	    }
	    return day;
	
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[M][N];
		visited = new boolean[M][N];
	
		for(int i = 0; i < M; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		System.out.println(bfs());

	}

}
