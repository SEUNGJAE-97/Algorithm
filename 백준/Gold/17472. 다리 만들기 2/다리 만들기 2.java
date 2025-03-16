import java.io.*;
import java.util.*;

public class Main {

	// * 1. 각 섬마다 번호 지정
	// * 2. 각 섬의 노드마다 가로, 세로로 쭉 이어서 닿는 섬이 있는지 확인
	// *2-1. 만약 이 거리가 2미만이면 패스
	// * 2-2. 2번을 반복하여 각 섬마다 연결되는 최소 거리의 합을 구한다.

	private static int N, M;
	private static int islandNum = 1;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int[] parent;
	private static List<Bridge> distance = new ArrayList<>();
	private static class Node {
		int x, y, number;
		public Node(int x, int y, int number) {
			super();
			this.x = x;
			this.y = y;
			this.number = number;
		}
	}
	private static class Bridge implements Comparable<Bridge> {
		int from, to, length;

		public Bridge(int from, int to, int length) {
			this.from = from;
			this.to = to;
			this.length = length;
		}

		@Override
		public int compareTo(Bridge o) {
			return this.length - o.length;
		}
	}
	private static void find_path(int x , int y) {
		int landNum = map[x][y];
		
		for(int d = 0; d <  4; d++) {
			int length = 0;
			int nx = x;
			int ny = y;
			while(true) {
				nx += dx[d];
				ny += dy[d];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) break;
				if(map[nx][ny] == landNum) break;
				if(map[nx][ny] == 0) length++;
				else if(map[nx][ny] != landNum) {
					if(length >= 2) {
						distance.add(new Bridge(landNum, map[nx][ny], length));
					}
					break;
				}
			}
			
		}
	}
	private static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB) return false;
		parent[rootB] = rootA;
		return true;
	}

	private static int kruskal() {
		Collections.sort(distance);
		parent = new int[islandNum];

		for (int i = 0; i < islandNum; i++) {
			parent[i] = i;
		}

		int totalLength = 0, count = 0;
		for (Bridge bridge : distance) {
			if (union(bridge.from, bridge.to)) {
				totalLength += bridge.length;
				count++;
			}
			if (count == islandNum - 2) break;
		}

		return count == islandNum - 2 ? totalLength : -1;
	}
	private static void dfs(int x, int y, int number) {
		Deque<Node> stack = new ArrayDeque<>();
		stack.offer(new Node(x, y, number));
		visited[x][y] = true;

		while (!stack.isEmpty()) {
			Node current = stack.poll();
			map[current.x][current.y] = current.number;
			visited[current.x][current.y] = true;
			for (int d = 0; d < 4; d++) {
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];
				if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && map[nx][ny] != 0) {
					visited[nx][ny] = true;
					stack.offer(new Node(nx, ny, current.number));
					map[nx][ny] = current.number;
				}
			}
		}
	}

	private static void printMatrix() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 1. dfs를 이용하여 섬 번호 지정하기 2~6개 사이의 번호로 지정됨
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0 && !visited[i][j]) {
					dfs(i, j, islandNum);
					islandNum++;
				}
			}
		}
		
		// 2. 각 섬의 노드마다 가로, 세로로 쭉 이어서 닿는 섬이 있는지 확인
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 0 && visited[i][j]) {
					find_path(i,j);
				}
			}
		}
		System.out.println(kruskal());
	}
}