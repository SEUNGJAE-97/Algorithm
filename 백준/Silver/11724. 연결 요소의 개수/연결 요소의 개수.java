import java.io.*;
import java.util.*;
public class Main {

	private static int N, M,Count;
	private static boolean visited[];
	private static ArrayList<ArrayList<Integer>> graph;
	
	private static void dfs(int v) {
		visited[v] = true;
		for(int neighbor : graph.get(v)) {
			if (!visited[neighbor]) {
                dfs(neighbor);
            }
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Count = 0;
        visited = new boolean[N + 1];
        graph = new ArrayList<>();
        
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                Count++;   
                dfs(i);
            }
        }
        System.out.println(Count);
	}

}
