import java.util.*;
import java.io.*;

public class Main {
	private static final int INF = 999999;
	private static int[][] distance;
	private static int[][] map;
	private static int N, Count;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	
	private static StringBuilder sb = new StringBuilder();
	
	private static class Node implements Comparable<Node>{
		int x, y, cost;
		
		public Node(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
		
	}
	private static void Dijkstra() {
		// 시작점은 0,0 
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0,0, map[0][0]));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			int x = current.x;
			int y = current.y;
			int cost = current.cost;
			
			// 목적지 도착시 했다면  
			if(x == N - 1 && y == N -1) {
				sb.append("Problem").append(" ").append(Count).append(":").append(" ").append(cost).append("\n");
				return;
			}
			
			// 4방향 탐색 
			for(int d = 0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny <N) {
					int nextCost = cost + map[nx][ny];
					
					if(nextCost < distance[nx][ny]) {
						distance[nx][ny] = nextCost;
						pq.offer(new Node(nx,ny, nextCost));
					}
				}
			}
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Count = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			map = new int[N][N];
			distance = new int[N][N];
			distance[0][0] = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					distance[i][j] = INF;
				}
			}
			
			// Dijkstra 구현
			Dijkstra();
			Count++;
		}
		System.out.println(sb);
	}
}