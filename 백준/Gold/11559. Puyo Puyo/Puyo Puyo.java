import java.io.*;
import java.util.*;

public class Main {
	private static String[][] matrix;
	private static int[] dx = {0,0,-1,1};
	private static int[] dy = {-1,1,0,0};
	private static boolean[][] visited;
	
	private static class Node {
		int x, y;
		
		private Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	private static boolean bfs(Node start) {
		Deque<Node> deque = new ArrayDeque<>();
		List<Node> targets = new ArrayList<>();
		visited[start.x][start.y] = true;
		deque.offer(start);
		targets.add(start);
		
		while(!deque.isEmpty()) {
			Node node = deque.poll();
			String puyo = matrix[node.x][node.y];
			for(int d = 0; d < 4; d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				if(nx >= 0 && ny >= 0 && nx < 12 && ny < 6 && !visited[nx][ny] && puyo.equals(matrix[nx][ny])){
					targets.add(new Node(nx, ny));
					deque.offer(new Node(nx, ny));
					visited[nx][ny] = true;
				}
 			}
		}
		// 4개이상이면 터트리고 .으로 변
		if(targets.size() >= 4) {
			for(Node n : targets) {
				matrix[n.x][n.y] = ".";
			}
			return true;
		}
		return false;
	}
	
	private static void applyGravity() {
		for (int j = 0; j < 6; j++) {
	        Deque<String> puyos = new ArrayDeque<>();
	        
	        // 1. 아래서부터 위로 올라가며 빈칸이 아닌 뿌요를 큐에 수집
	        for (int i = 11; i >= 0; i--) {
	            if (!matrix[i][j].equals(".")) {
	                puyos.offer(matrix[i][j]);
	                matrix[i][j] = ".";
	            }
	        }
	        
	        // 2. 다시 아래서부터 큐에 담긴 뿌요를 하나씩 채움
	        int index = 11;
	        while (!puyos.isEmpty()) {
	            matrix[index][j] = puyos.poll();
	            index--;
	        }
	    }
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		matrix = new String[12][6];
		
		for(int i = 0; i < 12; i ++) {
			String line = br.readLine();
			for(int j = 0; j < 6; j++) {
				matrix[i][j] = String.valueOf(line.charAt(j));
			}
		}
		int answer = 0;
		while(true) {
			boolean isPopped = false;
			visited = new boolean[12][6];
			
			for(int i=0; i<12; i++) {
			    for(int j=0; j<6; j++) {
			        if(!matrix[i][j].equals(".") && !visited[i][j]) {
			            if(bfs(new Node(i,j))) {
			                isPopped = true;
			            }
			        }
			    }
			}
			if(!isPopped) break;
			answer++;
			applyGravity();
		}
		System.out.println(answer);
	}
}
