import java.util.*;
import java.io.*;

public class Solution {
	static List<String> left = new ArrayList<>(Arrays.asList("{", "[", "(", "<"));
	static List<String> right = new ArrayList<>(Arrays.asList("}", "]", ")", ">"));

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int TestCase = 1; TestCase <= 10; TestCase++) {
			sb.append("#").append(TestCase).append(" ");
			// init
			int N = Integer.parseInt(br.readLine());
			int result = 0;
			// {, [, (, <
			int[] check = new int[4];
			String[] array = br.readLine().split("");
			// 구현
			for (int i = 0; i < N; i++) {
				if (left.contains(array[i])) {
					switch (array[i]) {
					case "{":
						check[0]++;
						break;
					case "[":
						check[1]++;
						break;
					case "(":
						check[2]++;
						break;
					case "<":
						check[3]++;
						break;
					}

				} else if (right.contains(array[i])) {
					switch (array[i]) {
					case "}":
						check[0]--;
						break;
					case "]":
						check[1]--;
						break;
					case ")":
						check[2]--;
						break;
					case ">":
						check[3]--;
						break;
					}
				}
			}
			for(int c : check) {
				if(c != 0) {
					result = 0;
					break;
				}
				else {
					result = 1;
				}
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}

}