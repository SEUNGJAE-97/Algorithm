import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

  private static int T, N, M, Result;
  private static boolean[][] V;

  private static boolean Check(int x) {
    for (int i = 1; i <= N; i++) {
      // 4. 관계가 성립하지 않거나, 자기자신은 배제
      if (!V[i][x] && !V[x][i] && x != i) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();

    // 0. 데이터 입력
    T = Integer.parseInt(br.readLine().trim());

    for (int tc = 1; tc <= T; tc++) {
      N = Integer.parseInt(br.readLine().trim());
      M = Integer.parseInt(br.readLine().trim());

      V = new boolean[N + 1][N + 1];

      for (int n = 0; n < M; n++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        V[a][b] = true;
      }
      // 1. visited 배열 완성
      for (int K = 1; K <= N; K++) {
        for (int i = 1; i <= N; i++) {
          for (int j = 1; j <= N; j++) {
            if (V[i][K] && V[K][j]) {
              V[i][j] = true;
            }
          }
        }
      }
      // 2. 모든 사람들과 키관계가 완전히 성립하는 관계에 대해서만 체크
      Result = 0;
      for (int i = 1; i <= N; i++) {
        if (Check(i)) {
          Result++;
        }
      }
      sb.append("#").append(tc).append(" ").append(Result).append("\n");
    }
    System.out.println(sb);
  }
}
