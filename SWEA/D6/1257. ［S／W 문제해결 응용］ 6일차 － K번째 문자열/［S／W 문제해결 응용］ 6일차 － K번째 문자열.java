import java.io.*;
import java.util.*;

public class Solution {
	private static int T, K;
	private static char[] charset;
	private static Set<String> dictionary;

	private static void subset(int start, int end) {
		if (start > end) {
			return;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = start; i <= end; i++) {
			sb.append(charset[i]);

			if (!dictionary.contains(sb.toString())) {

				dictionary.add(sb.toString());
			}
		}
		subset(start + 1, end);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder s = new StringBuilder();
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(br.readLine());
			String split = br.readLine();

			charset = new char[split.length()];
			dictionary = new HashSet<>();

			for (int i = 0; i < split.length(); i++) {
				charset[i] = (char) (split.charAt(i));
			}

			subset(0, split.length() - 1);
			 List<String> sortedList = new ArrayList<>(dictionary);
			Collections.sort(sortedList);

			if (dictionary.size() <= K - 1) {
				s.append("#").append(tc).append(" ").append("none").append("\n");
			} else {
				s.append("#").append(tc).append(" ").append(sortedList.get(K - 1)).append("\n");
			}

//			System.out.println(dictionary);
		}
		System.out.println(s);
	}
}
