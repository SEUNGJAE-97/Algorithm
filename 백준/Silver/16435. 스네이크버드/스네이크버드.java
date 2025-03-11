import java.io.*;
import java.util.*;

public class Main {
	private static List<Integer> fruits;
	private static int fruit, snake;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		fruit = Integer.parseInt(st.nextToken());
		snake = Integer.parseInt(st.nextToken());
		fruits = new ArrayList<>();
		
		String[] split = br.readLine().split(" ");
		
		for(int i = 0; i < fruit; i ++) {
			fruits.add(Integer.parseInt(split[i])); 
		}
		
		Collections.sort(fruits, Collections.reverseOrder());
		
		while(!fruits.isEmpty()) {
			// 종료조건
			if(fruits.get(fruits.size()-1) > snake) break;
			for(int i = fruits.size()-1; i >=0; i--) {
				if(snake >= fruits.get(i)) {
					snake++;
					fruits.remove(i);
				}
			}
		}
		
		 System.out.println(snake); 
	}
}