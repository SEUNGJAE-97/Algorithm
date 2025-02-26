import java.io.*;
import java.util.*;

public class Solution {
    private static int T, V, E;
    private static Edge[] edgeList;
    private static int[] parents; 

    private static class Edge implements Comparable<Edge> {
        int from, to;
        long weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
        	return Long.compare(this.weight, o.weight); 
        }
    }

    private static void makeSet() {
        parents = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    }

    private static int findSet(int a) {
        if (parents[a] == a) return a;
        return parents[a] = findSet(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            if (E == 0) {  // 간선이 아예 없을 때
                sb.append("#").append(tc).append(" ").append(0).append("\n");
                continue;
            }

            edgeList = new Edge[E];
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                edgeList[i] = new Edge(from, to, weight);
            }

            Arrays.sort(edgeList);
            makeSet();

            long result = 0L;
            int count = 0;
            for (Edge edge : edgeList) {
                if (union(edge.from, edge.to)) {
                    result += edge.weight;
                    if (++count == V - 1) break;
                }
            }

            if (count < V - 1) {
                sb.append("#").append(tc).append(" ").append(-1).append("\n"); // MST 불가능한 경우
            } else {
                sb.append("#").append(tc).append(" ").append(result).append("\n");
            }
        }
        System.out.println(sb);
    }
}