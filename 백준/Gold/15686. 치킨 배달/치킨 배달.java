import java.util.*;

public class Main {
	static int N, M;
	static List<int[]> houses = new ArrayList<>();
	static List<int[]> chickens = new ArrayList<>();
	static int minDistance = Integer.MAX_VALUE;

	private static void combination(int start, int depth, List<int[]> selected) {
		if (depth == M) {
			minDistance = Math.min(minDistance, getCityDistance(selected));
			return;
		}
		for (int i = start; i < chickens.size(); i++) {
			selected.add(chickens.get(i));
			combination(i + 1, depth + 1, selected);
			selected.remove(selected.size() - 1);
		}
	}

	private static int getCityDistance(List<int[]> selected) {
		int totalDistance = 0;
		for (int[] house : houses) {
			int minDist = Integer.MAX_VALUE;
			for (int[] chicken : selected) {
				int dist = Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
				minDist = Math.min(minDist, dist);
			}
			totalDistance += minDist;
		}
		return totalDistance;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		int[][] city = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				city[i][j] = sc.nextInt();
				if (city[i][j] == 1)
					houses.add(new int[] { i, j });
				else if (city[i][j] == 2)
					chickens.add(new int[] { i, j });
			}
		}

		// M개의 치킨집을 선택하는 조합 수행
		combination(0, 0, new ArrayList<>());
		System.out.println(minDistance);
	}

}