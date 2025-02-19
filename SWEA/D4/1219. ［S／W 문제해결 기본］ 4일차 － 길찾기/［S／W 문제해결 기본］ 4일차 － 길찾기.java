import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class Solution {
  private static boolean result;
  private static int N;
  static HashMap<Integer, List<Integer>> map = new HashMap<>();
  static boolean[] visited = new boolean[100];

  private static void dfs(int start) {
    Deque<Integer> deque = new ArrayDeque<>();
    deque.push(start);
    visited[start] = true;

    while (!deque.isEmpty()) {
      int current = deque.pop();
      if (current == 99) {
        result = true;
        return;
      }

      if (map.containsKey(current)) {
        List<Integer> nextNodes = map.get(current);
        for (int next : nextNodes) {
          if (!visited[next]) {
            visited[next] = true;
            deque.push(next);
          }
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    //System.setIn(new FileInputStream("input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    for (int TestCase = 1; TestCase <= 10; TestCase++) {
      sb.append("#").append(TestCase).append(" ");
      String[] list = br.readLine().split(" ");
      N = Integer.parseInt(list[1]);
      String[] array = br.readLine().split(" ");
      result = false;
      map.clear();
      for (int i = 0; i < visited.length; i++) {
        visited[i] = false;
      }

      for (int i = 0; i < array.length / 2; i++) {
        int start = Integer.parseInt(array[i * 2]);
        int end = Integer.parseInt(array[i * 2 + 1]);
        map.putIfAbsent(start, new ArrayList<>());
        map.get(start).add(end);
      }

      dfs(2); // 시작점 2로 변경

      if (result) {
        sb.append("1").append("\n");
      } else {
        sb.append("0").append("\n");
      }
    }
    System.out.println(sb);
  }
}