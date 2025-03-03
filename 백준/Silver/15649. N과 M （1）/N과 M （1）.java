import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] numbers;
	private static boolean[] visited;
	private static void permutation(int cnt) {
		if(cnt == M) {
			for(int num : numbers) {
				System.out.print(num);
				System.out.print(" ");
			}
			System.out.println();
			return;
		}
		for(int i = 1; i <= N; i ++) {
			if(!visited[i]) {
				visited[i] = true;
				numbers[cnt] = i;
				permutation(cnt+1);
				visited[i] = false;
			}
		}
	}
	public static void main(String[] args) throws Exception{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[M];
		visited = new boolean[N+1];
		permutation(0);
		
	}
}