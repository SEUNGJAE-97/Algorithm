import java.util.*;
import java.io.*;

public class Solution {
  static int N;
  private static Object[] nodes; // 정점 저장 배열
  private static int lastIndex; // 정점 저장 배열에서 마지막 정점 저장된 인덱스 번호
  // 중위순회

  public static void dfsByInOrder() {
    dfsByInOrder(1);
  }

  // current: 현재 방문한 정점 번호
  public static void dfsByInOrder(int current) {

    // 왼쪽(L)
    if (current * 2 <= lastIndex) {
      dfsByInOrder(current * 2);
    }

    // 정점 방문해서 처리해야 할 일 수행 (V)
    System.out.print(nodes[current]);

    // 오른쪽(R)
    if (current * 2 + 1 <= lastIndex) {
      dfsByInOrder(current * 2 + 1);
    }
  }

  public static void main(String[] args) throws Exception {
    //System.setIn(new FileInputStream("input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();

    for (int tc = 1; tc <= 10; tc++) {
      N = Integer.parseInt(br.readLine());
      lastIndex = N;
      nodes = new Object[N + 1];
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine(), " ");
        nodes[Integer.parseInt(st.nextToken())] = st.nextToken();
      }
      System.out.printf("#%d ", tc);
      dfsByInOrder();
      System.out.println();

    }
  }

}