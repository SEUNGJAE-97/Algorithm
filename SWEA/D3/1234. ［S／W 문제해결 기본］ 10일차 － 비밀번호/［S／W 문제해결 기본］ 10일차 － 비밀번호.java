import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
	private static ArrayList<Integer> arr = new ArrayList<>();
	private static int N;
	private static boolean flag;
	private static void calc(ArrayList<Integer> arr) {
        boolean changed;
        do {
            changed = false;
            for (int i = 0; i < arr.size() - 1; i++) {
                if (arr.get(i).equals(arr.get(i + 1))) {
                    arr.remove(i);
                    arr.remove(i);  // 두 번째 요소가 한 칸 앞당겨졌으므로 같은 인덱스 삭제
                    changed = true;
                    break;  // 리스트가 변경되었으므로 다시 처음부터 검사
                }
            }
            //System.out.println(arr);
        } while (changed); // 리스트가 변경되었을 때만 다시 실행
    }

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 10; tc++) {
			sb.append("#").append(tc).append(" ");
			// init
			String[] testArr = br.readLine().split(" ");
			arr.clear();
			N = Integer.parseInt(testArr[0]);
			for (int i = 0; i < N; i++) {
				arr.add(Integer.parseInt((String.valueOf(testArr[1].charAt(i)))));
			}
			// 구현
			calc(arr);
			String result = arr.stream().map(String::valueOf).collect(Collectors.joining(""));
			// System.out.println(testArr[0]+testArr[1]);
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
}