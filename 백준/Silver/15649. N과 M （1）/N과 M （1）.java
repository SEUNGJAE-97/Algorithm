import java.util.*;
import java.io.*;

public class Main {
  private static int N, M;
  private static int[] numbers;
  private static boolean[] visited;

  private static void permutation(int depth) {
    if (depth == M) {
      for (int num : numbers) {
        System.out.print(num + " ");
      }
      System.out.println();
      return;
    }

    for (int i = 1; i <= N; i++) {
      if (!visited[i]) {
        visited[i] = true;
        numbers[depth] = i;
        permutation(depth + 1);
        visited[i] = false;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] input = br.readLine().split(" ");
    N = Integer.parseInt(input[0]);
    M = Integer.parseInt(input[1]);

    numbers = new int[M];
    visited = new boolean[N + 1];

    permutation(0);
  }

}