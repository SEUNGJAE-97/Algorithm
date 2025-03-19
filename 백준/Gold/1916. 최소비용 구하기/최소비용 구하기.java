import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, station_s, station_e;
	private static List<Bus>[] graph;
	private static int[] distance;
	
	private static class Bus implements Comparable<Bus> {
		int index, cost;

		public Bus(int index, int cost) {
			super();
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo(Bus o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost);
		}

		@Override
		public String toString() {
			return "Bus [index=" + index + ", cost=" + cost + "]";
		}
	}

	private static void dijkstra(int start) {
		PriorityQueue<Bus> pq = new PriorityQueue<>();
		pq.add(new Bus(start, 0));
		distance[start] = 0;
		
		while(!pq.isEmpty()) {
			Bus current = pq.poll();
			int cur = current.index;
			int curCost = current.cost;
			
			if(curCost > distance[cur]) continue;
			for(Bus bus : graph[cur]) {
				int nextCost = curCost + bus.cost;
				if(nextCost < distance[bus.index]) {
					distance[bus.index] = nextCost;
					pq.add(new Bus(bus.index, nextCost));
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[N+1];
		for(int i = 1; i <=N; i++) graph[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[start].add(new Bus(end, cost));
		}

		st = new StringTokenizer(br.readLine());
		station_s = Integer.parseInt(st.nextToken());
		station_e = Integer.parseInt(st.nextToken());
		
		distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		dijkstra(station_s);
		
		System.out.println(distance[station_e]);
		
	}

}