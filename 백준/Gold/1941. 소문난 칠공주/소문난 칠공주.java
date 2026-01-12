import java.io.*;
import java.util.*;

public class Main {
	private static char[][] matrix;
	private static List<Node> allNodes;
	private static Node[] selected = new Node[7];
	private static int[] dx = {0,0,-1,1};
	private static int[] dy = {-1,1,0,0};
	private static int ans = 0;
	
	private static class Node{
		int x,y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	private static void comb(int start, int depth, int sCnt) {
		if (depth == 7) {
	        if (sCnt >= 4) { 
	            if (isConnected()) ans++;
	        }
	        return;
	    }

	    for (int i = start; i < 25; i++) {
	        selected[depth] = allNodes.get(i);
	        int nextSCnt = sCnt + (matrix[selected[depth].x][selected[depth].y] == 'S' ? 1 : 0);
	       comb(i + 1, depth + 1, nextSCnt);
	    }
	}
	
	private static boolean isConnected() {
	    Queue<Node> q = new LinkedList<>();
	    boolean[] visited = new boolean[7];
	    
	    q.add(selected[0]);
	    visited[0] = true;
	    int count = 1;

	    while (!q.isEmpty()) {
	        Node cur = q.poll();

	        for (int i = 0; i < 4; i++) {
	            int nx = cur.x + dx[i];
	            int ny = cur.y + dy[i];
	            for (int j = 0; j < 7; j++) {
	                if (!visited[j] && selected[j].x == nx && selected[j].y == ny) {
	                    visited[j] = true;
	                    q.add(selected[j]);
	                    count++;
	                }
	            }
	        }
	    }

	    return count == 7; 
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// init
		matrix = new char[5][5];
	    for(int i = 0; i < 5; i++) {
	        String line = br.readLine();
	        for(int j = 0; j < 5; j++) {
	            matrix[i][j] = line.charAt(j);
	        }
	    }
	
		allNodes = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
		    for(int j = 0; j < 5; j++) {
		        allNodes.add(new Node(i, j));
		    }
		}
		
		// sol
		comb(0,0,0);
		
		System.out.println(ans);
		
	}

}
