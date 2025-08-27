import java.io.*;
import java.util.*;

public class Main {
    private static class Node {
        int n;
        int m;

        public Node(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }

    // 맵 사이즈 N, M
    // 사각형 개수 K
    private static int N, M, K;
    private static Queue<Node> queue;
    private static boolean[][] map;
    private static int[] x = {-1, 1, 0, 0};
    private static int[] y = {0, 0, -1, 1};
//    private static StringBuilder sb = new StringBuilder();
    private static ArrayList<Integer> list;

    private static int dfs(int n, int m) {
        queue = new ArrayDeque<>();
        queue.offer(new Node(n, m));
        int result = 0;
        while (!queue.isEmpty()) {
            Node start = queue.poll();
            if (!map[start.n][start.m]) {
                map[start.n][start.m] = true;
                result++;
                for (int d = 0; d < 4; d++) {
                    int dx = x[d] + start.n;
                    int dy = y[d] + start.m;
                    if (dx >= 0 && dy >= 0 && dx < N && dy < M && !map[dx][dy]) {
                        queue.offer(new Node(dx, dy));
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        list  = new ArrayList<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int result = 0;
        map = new boolean[N][M];

        // init
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int xx = Integer.parseInt(st.nextToken());
            int yy = Integer.parseInt(st.nextToken());

            for (int n = y; n < yy; n++) {
                for (int m = x; m < xx; m++) {
                    map[n][m] = true;
                }
            }
        }


        // 구현
        // 1. map을 돌면서 false인 곳을 발견하면 dfs 탐색
        // 2. dfs 결과 값 (땅 크기 반환) 추가한다.
        // 3. 이때 이미 지나온 땅은 true로 바꿔줘야함
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!map[i][j]) {
                    list.add(dfs(i, j));
                    result++;
                }
            }
        }

        System.out.println(result);
        list.sort( Comparator.naturalOrder());
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }
}