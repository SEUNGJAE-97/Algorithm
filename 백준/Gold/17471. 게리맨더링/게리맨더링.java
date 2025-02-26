import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] Population;
	private static List<Integer>[] graph;
	static int Diff;

	// 2. 나눠진 지역이 두개의 구역으로 연결되는지 확인 (union을 통해 일치하는 부모노드끼리 일치하는지 확인)
	private static boolean isConnected(List<Integer> group) {
		if (group.isEmpty())
			return false;
		Queue<Integer> queue = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();

		queue.add(group.get(0)); // 그룹의 첫 번째 노드부터 BFS 시작
		visited.add(group.get(0));

		while (!queue.isEmpty()) {
			int node = queue.poll();
			for (int neighbor : graph[node]) {
				if (group.contains(neighbor) && !visited.contains(neighbor)) {
					visited.add(neighbor);
					queue.add(neighbor);
				}
			}
		}

		return visited.size() == group.size(); // 방문한 노드 수 == 그룹 크기이면 연결된 것
	}

	// 3. 두 지역의 차이 값 중에서 최소값을 출력
	private static int getPopulation(List<Integer> group) {
		int sum = 0;
		for (int idx : group) {
			sum += Population[idx];
		}
		return sum;
	}

	public static void main(String[] args) throws Exception {
		// 0.init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		Population = new int[N];
		graph = new ArrayList[N];
		Diff = Integer.MAX_VALUE;

		String[] split = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			Population[i] = Integer.parseInt(split[i]);
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken()); // 연결된 구역 개수
			for (int j = 0; j < count; j++) {
				graph[i].add(Integer.parseInt(st.nextToken()) - 1); // 0-indexed 변환
			}
		}

		// 1.지역 두개의 선거구 중에 하나를 할당
		for (int i = 1; i < (1 << N) - 1; i++) {
			List<Integer> groupA = new ArrayList<>();
			List<Integer> groupB = new ArrayList<>();

			for (int j = 0; j < N; j++) {
				if ((i & (1 << j)) > 0)
					groupA.add(j); // i의 j번째 비트가 1이면 A 선거구
				else
					groupB.add(j); // 아니면 B 선거구
			}

			// 두 그룹이 모두 연결되어 있는지 확인
			if (isConnected(groupA) && isConnected(groupB)) {
				int popA = getPopulation(groupA);
				int popB = getPopulation(groupB);
				Diff = Math.min(Diff, Math.abs(popA - popB));
			}
		}
		System.out.println(Diff == Integer.MAX_VALUE ? -1 : Diff);

	}
}