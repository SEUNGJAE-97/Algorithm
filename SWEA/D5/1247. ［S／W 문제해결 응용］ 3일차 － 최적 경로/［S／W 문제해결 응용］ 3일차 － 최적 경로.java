import java.io.*;
import java.util.*;

class Node {
	int x;
	int y;

	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class Solution {
	private static int T, N;
	private static Node[] customers;
	private static int distance;
	private static Node start, end;
	private static boolean[] visited;

	private static void dfs(int depth, int prevX, int prevY, int totalDist) {
		// 모든 고객을 방문했으면 집까지 이동
		if (depth == N) {
			// 마지막으로 집 좌표까지 얼마나 걸리는지 계산
			totalDist += Math.abs(prevX - end.x) + Math.abs(prevY - end.y);
			distance = Math.min(distance, totalDist);
			return;
		}

		for (int i = 0; i < N; i++) {
			// 방문하지 않은 손님부터
			if (!visited[i]) {
				// 방문처리
				visited[i] = true;
				// 거리계산
				int dist = Math.abs(prevX - customers[i].x) + Math.abs(prevY - customers[i].y);
				// 다음 방문하지 않은 손님에게 찾아가고, 거리 갱신
				dfs(depth + 1, customers[i].x, customers[i].y, totalDist + dist);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			customers = new Node[N];
			distance = Integer.MAX_VALUE;
			visited = new boolean[N];

			// 첫 번째 줄 (회사, 집, 사람들) 정보를 처리
			String[] split = br.readLine().split(" ");

			start = new Node(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
			end = new Node(Integer.parseInt(split[2]), Integer.parseInt(split[3]));

			for (int i = 0, idx = 4; i < N; i++, idx += 2) {
				customers[i] = new Node(Integer.parseInt(split[idx]), Integer.parseInt(split[idx + 1]));
			}
			dfs(0, start.x, start.y, 0);

			sb.append("#").append(tc).append(" ").append(distance).append("\n");

		}
		System.out.println(sb);

	}
}