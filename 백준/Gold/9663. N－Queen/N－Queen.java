import java.io.*;

public class Main {
    private static int N;
    private static boolean[][] board;
    private static int count = 0;
    
    private static boolean check(int row, int col) {
        // 위쪽 열 검사
        for (int i = 0; i < row; i++) {
            if (board[i][col]) return false;
        }
        // 좌상 대각선 검사
        for (int i = row -1, j = col -1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j]) return false;
        }
        // 우상 대각선 검사
        for (int i = row -1, j = col +1; i >= 0 && j < N; i--, j++) {
            if (board[i][j]) return false;
        }
        return true;
    }
    
    private static void dfs(int row) {
        if (row == N) {
            count++;
            return;
        }
        
        for (int col = 0; col < N; col++) {
            if (check(row, col)) {
                board[row][col] = true;  // 퀸 놓기
                dfs(row + 1);             // 다음 행 탐색
                board[row][col] = false; // 백트래킹: 되돌리기
            }
        }
    }
    
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        board = new boolean[N][N];
        
        dfs(0);
        System.out.println(count);
    }
}
