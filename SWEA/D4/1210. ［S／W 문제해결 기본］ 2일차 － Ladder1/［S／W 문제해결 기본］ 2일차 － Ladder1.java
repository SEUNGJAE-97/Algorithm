import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
  private static int result;

  public static int sadari(int[][] array) {
    int current_index = 0;
    // int[][] destination = new int[100][1];
    // 목적지 인덱스 찾기
    for (int i = 0; i < 100; i++) {
      if (array[99][i] == 2) {
        current_index = i;
        break;
      }
    }
    // 올라가기,좌우체크
    for (int i = 99; i > 0; i--) {
      // 왼 쪽
      if (current_index > 0 && array[i][current_index - 1] == 1) {
        // 왼쪽으로 더 갈 수 있다면 계속 이동
        while (current_index > 0 && array[i][current_index - 1] == 1) {
          current_index--;
        }
      }
      // 오른쪽
      else if (current_index < 99 && array[i][current_index + 1] == 1) {
        // 오른쪽으로 갈 수 있다면 계속 이동
        while (current_index < 99 && array[i][current_index + 1] == 1) {
          current_index++;
        }
      }
    }
    return current_index;
  }

  public static void main(String[] args) throws Exception {
    //System.setIn(new FileInputStream("input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    for (int testCase = 1; testCase <= 10; testCase++) {
      sb.append("#").append(testCase).append(" ");
      // init
      int N = Integer.parseInt(br.readLine());
      int[][] array = new int[100][100];

      for (int i = 0; i < 100; i++) {
        String[] line = br.readLine().split(" ");
        for (int j = 0; j < 100; j++) {
          array[i][j] = Integer.parseInt(line[j]);
        }
      }
      // 연산
      result = sadari(array);
      sb.append(result).append("\n");
    }

    System.out.println(sb);

  }

}