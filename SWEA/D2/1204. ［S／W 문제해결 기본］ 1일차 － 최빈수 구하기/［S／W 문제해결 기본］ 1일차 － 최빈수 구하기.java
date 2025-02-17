import java.io.*;
import java.util.*;

public class Solution {


  private static Integer[] counter;

  public static void main(String[] args) throws Exception {

    /*
     * 0. 입력파일 읽어들이기
     */
    //System.setIn(new FileInputStream("input.txt"));
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    // 결과를 한 번에 출력하기 위한 StringBuilder
    StringBuilder sb = new StringBuilder();

    int T;
    T = Integer.parseInt(in.readLine());
    for (int test_case = 1; test_case <= T; test_case++) {
      sb.append("#" + test_case + " ");

      // 여러분의 알고리즘 코드 작성하기
      /* 1. 입력 파일 객체화 */
      in.readLine(); // 테케번호는 읽어서 건너뛰기
      String[] scores = in.readLine().split(" ");// split() " "로 구분하여 배열을 리턴함
      counter = new Integer[101];
      Arrays.fill(counter, 0);
      /* 2. 알고리즘 풀기 */
      for (int i = 0; i < scores.length; i++) {
        int score = Integer.parseInt(scores[i]);
        counter[score]++; // 점수 카운팅
      }

      /* 3. 정답 출력 */
      int maxCount = 0;
      int mode = 0;

      for (int i = 0; i < counter.length; i++) {
        if (counter[i] > maxCount || (counter[i] == maxCount && i > mode)) {
          maxCount = counter[i];
          mode = i;
        }
      }
      sb.append(mode).append("\n");

    }
    System.out.println(sb);
  }
}