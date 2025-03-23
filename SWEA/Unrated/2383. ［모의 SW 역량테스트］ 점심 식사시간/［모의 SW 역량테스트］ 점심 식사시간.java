import java.io.*;
import java.util.*;

public class Solution {
	private static int N, minTime;
	private static List<int[]> people, stairs;
	static int simulate(List<int[]> group, int[] stair) {
		if (group.isEmpty())
			return 0;

		List<Integer> arrivalTimes = new ArrayList<>();

		for (int[] person : group) {
			int distance = Math.abs(person[0] - stair[0]) + Math.abs(person[1] - stair[1]);
			arrivalTimes.add(distance);
		}

		Collections.sort(arrivalTimes);

		Queue<Integer> stairQueue = new LinkedList<>();
		int stairLength = stair[2];
		int time = 0, index = 0;

		while (index < arrivalTimes.size() || !stairQueue.isEmpty()) {
			while (!stairQueue.isEmpty() && stairQueue.peek() == time) {
				stairQueue.poll();
			}

			while (index < arrivalTimes.size() && stairQueue.size() < 3 && arrivalTimes.get(index) <= time) {
				stairQueue.add(time + stairLength);
				index++;
			}

			time++;
		}

		return time;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			people = new ArrayList<>();
			stairs = new ArrayList<>();

			// 입력 받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int val = Integer.parseInt(st.nextToken());
					if (val == 1) {
						people.add(new int[] { i, j });
					} else if (val > 1) {
						stairs.add(new int[] { i, j, val });
					}
				}
			}

			minTime = Integer.MAX_VALUE;

			// 비트마스크를 이용해서 계단 선택 (모든 경우의 수 탐색)
			int peopleCount = people.size();
			for (int mask = 0; mask < (1 << peopleCount); mask++) {
				List<int[]> groupA = new ArrayList<>();
				List<int[]> groupB = new ArrayList<>();

				for (int i = 0; i < peopleCount; i++) {
					if ((mask & (1 << i)) == 0) {
						groupA.add(people.get(i)); // 계단 A로 가는 그룹
					} else {
						groupB.add(people.get(i)); // 계단 B로 가는 그룹
					}
				}

				int timeA = simulate(groupA, stairs.get(0));
				int timeB = simulate(groupB, stairs.get(1));

				minTime = Math.min(minTime, Math.max(timeA, timeB));
			}
			sb.append("#").append(tc).append(" ").append(minTime).append("\n");
		}
		System.out.println(sb);
	}


}