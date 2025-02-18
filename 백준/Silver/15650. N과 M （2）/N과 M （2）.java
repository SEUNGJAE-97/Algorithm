import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

  private static int N, M;
  private static int[] numbers;

  private static void combination(int depth, int start) {
    if (depth == M) {
      for (int num : numbers) {
        System.out.print(num + " ");
      }
      System.out.println();
      return;
    }

    for (int i = start; i <= N; i++) {
      numbers[depth] = i;
      combination(depth + 1, i + 1);
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] input = br.readLine().split(" ");
    N = Integer.parseInt(input[0]);
    M = Integer.parseInt(input[1]);

    numbers = new int[M];
    combination(0, 1);
  }
}