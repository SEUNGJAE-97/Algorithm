import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static boolean[][] graph;
    private static int[] counts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new boolean[N][N];
        counts = new int[N];

        // init
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start-1][end-1] = true;
        }
        // 풀이
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (graph[i][k] && graph[k][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] || graph[j][i]) {
                    counts[i]++;
                }
            }
        }
        int result = 0;
        for (int n : counts) {
            if (n == N-1) {
                result++;
            }
        }

        System.out.println(result);
    }
}