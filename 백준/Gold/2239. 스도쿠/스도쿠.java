import java.io.*;
import java.util.*;

public class Main {
	
	private static int[][] matrix;
	private static List<Node> brank;
	private static class Node{
		int x, y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	private static void solution(int idx) {
		if(idx == brank.size()) {
			for(int i =0; i < 9; i ++) {
				for(int j =0; j < 9; j++) {
					System.out.print(matrix[i][j]);
				}
				System.out.println();
			}
			
			System.exit(0);
		}
		
		Node cur = brank.get(idx);
		int x = cur.x;
		int y = cur.y;
		
		for(int i = 1; i <= 9; i++) {
			if(isValid(x, y, i)) {
				matrix[x][y] = i;
				solution(idx + 1);
				matrix[x][y] = 0;
			}
		}
	}
	
	private static boolean isValid(int x, int y, int num) {
		for(int i =0; i < 9; i++) {
			// 가로체크 
			if(matrix[x][i] == num) return false;
			// 세로체크
			if(matrix[i][y] == num) return false;
		}
		// 3x3 안에 같은 값이 있는지
		int startRow = (x / 3) * 3;
	    int startCol = (y / 3) * 3;
	    for (int i = startRow; i < startRow + 3; i++) {
	        for (int j = startCol; j < startCol + 3; j++) {
	            if (matrix[i][j] == num) return false;
	        }
	    }
		return true;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		// init 
		matrix = new int[9][9];
		brank = new ArrayList<Node>();
		for(int i =0; i < 9; i++) {
			String line = br.readLine();
			for(int j = 0; j < 9; j++) {
				int num = line.charAt(j) - '0';
				matrix[i][j] = num;
				if(num == 0) brank.add(new Node(i, j));
			}
		}
		
		//sol 
		solution(0);
	}
}
