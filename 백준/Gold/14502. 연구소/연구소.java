import java.io.*;
import java.util.*;

public class Main {
	// 01. 저장하면서 바이러스가 존재하는 칸을 체크
	// 02. 0인 곳에 대해서만 노드를 저장한다.
	// 03. 노드 0인 곳을 조합으로 3개를 뽑는다.
	// 04. 세개인 곳을 모두 1로 바꾸고 바이러스로부터 dfs로
	// 탐색을 시작하여 최소가 되는 결과를 갱신한다.
	private static int N, M;
	private static int[][] Map;
	private static List<Node> Target;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static Deque<List<Node>> result = new ArrayDeque<>();
	private static int minVirus = Integer.MIN_VALUE;

	private static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
	}

	private static void printMatrix(int[][] temp) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(temp[i][j]);
			}
			System.out.println();
		}
	}

	private static int dfs(int[][] temp, int startX, int startY) {
		Deque<Node> stack = new ArrayDeque<>();
		stack.offer(new Node(startX, startY));
		int count = 1;

		while (!stack.isEmpty()) {
			Node current = stack.poll();
			for (int d = 0; d < 4; d++) {
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];

				if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
					if (temp[nx][ny] == 0) {
						temp[nx][ny] = 2;
						stack.offer(new Node(nx, ny));
						count++;
					}
				}
			}
//			printMatrix(temp);
//			System.out.println();
		}
		return count;
	}

	private static void combination(List<Node> temp, int start, int r) {
		if (temp.size() == r) {
			result.add(new ArrayList<>(temp));
			return;
		}
		for (int i = start; i < Target.size(); i++) {
			temp.add(Target.get(i));
			combination(temp, i + 1, r);
			temp.remove(temp.size() - 1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Map = new int[N][M];
		Target = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				if (Map[i][j] == 0)
					Target.add(new Node(i, j));
			}
		}

		// 노드가 0인 곳을 조합으로 3개 뽑기
		combination(new ArrayList<>(), 0, 3);

//		/*test*/
//		for (List<Node> comb : result) {
//			System.out.println(comb);
//		}
		// for문을 돌면서 해당 위치가 2(virus)인곳에 대하여 dfs 실행
		while (!result.isEmpty()) {
			List<Node> current = result.poll();
			int cnt = 0;
			int[][] temp = new int[N][M];
			
			// 표시이후 원복하기 위해 복사본 사용
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					temp[i][j] = Map[i][j];
				}
			}
			// 하나를 뽑아 1로 표시한다. (current.size = 3)
			for (int c = 0; c < current.size(); c++) {
				Node cc = current.get(c);
				temp[cc.x][cc.y] = 1;
			}
			// 탐색 (임의의 가벽을 3개 세운뒤 최대 바이러스가 얼마나 번지는지 확인)
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 바이러스 발견 시 dfs 실행
					if (temp[i][j] == 2) {
						// 자기자신까지 더해야함. 
						dfs(temp, i, j);
					}
				}
			}
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(temp[i][j] == 0) {
						cnt++;
					}
				}
			}
			minVirus = Math.max(cnt, minVirus);
		}
		System.out.println(minVirus);
	}
}
