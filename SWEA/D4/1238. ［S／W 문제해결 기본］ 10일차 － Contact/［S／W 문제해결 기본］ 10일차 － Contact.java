import java.io.*;
import java.util.*;

public class Solution {
  private static Queue<Integer> queue;
  private static int L;
  private static int S;
  private static boolean[] V;
  private static Map<Integer, List<Integer>> map;

  public static void main(String[] args) throws Exception {
    //System.setIn(new FileInputStream("input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    for (int tc = 1; tc <= 10; tc++) {
      String[] split = br.readLine().split(" ");
      String[] data = br.readLine().split(" ");

      L = Integer.parseInt(split[0]);
      S = Integer.parseInt(split[1]);

      queue = new ArrayDeque<>(); // 큐 초기화
      V = new boolean[101]; // 방문 배열 초기화
      map = new HashMap<>(); // 맵 초기화

      queue.offer(S);
      V[S] = true;

      // 입력 데이터 저장
      for (int i = 0; i < L; i += 2) {
        int key = Integer.parseInt(data[i]);
        int value = Integer.parseInt(data[i + 1]);

        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(value);
      }

      int NUM_MAX = 0; // 마지막 depth에서의 최댓값 저장

      // BFS 수행
      while (!queue.isEmpty()) {
        int size = queue.size();
        int maxAtThisLevel = 0; // 현재 depth에서 최대값 저장

        for (int i = 0; i < size; i++) {
          int current = queue.poll();
          maxAtThisLevel = Math.max(maxAtThisLevel, current);

          if (map.containsKey(current)) {
            for (int next : map.get(current)) {
              if (!V[next]) {
                queue.offer(next);
                V[next] = true;
              }
            }
          }
        }
        NUM_MAX = maxAtThisLevel; // 마지막 depth에서의 최댓값 저장
      }
      sb.append("#").append(tc).append(" ").append(NUM_MAX).append("\n");
    }
    System.out.println(sb);
  }
}