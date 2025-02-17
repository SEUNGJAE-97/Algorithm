import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int T = 10; // SWEA 문제에서 항상 10개 테스트케이스 고정
    for (int test_case = 1; test_case <= T; test_case++) {
      sb.append("#").append(test_case).append(" ");

      int dump = Integer.parseInt(in.readLine()); // 덤프 횟수
      StringTokenizer st = new StringTokenizer(in.readLine()); // 빠른 입력 처리
      int[] counter = new int[100]; // 박스 높이 (100개 고정)

      // 입력값을 정수 배열로 변환
      for (int i = 0; i < 100; i++) {
        counter[i] = Integer.parseInt(st.nextToken());
      }

      // 덤프 진행
      for (int i = 0; i < dump; i++) {
        int maxIdx = 0, minIdx = 0;

        // 최대값, 최소값 위치 찾기
        for (int j = 1; j < 100; j++) {
          if (counter[j] > counter[maxIdx])
            maxIdx = j;
          if (counter[j] < counter[minIdx])
            minIdx = j;
        }

        // 평탄화 완료되면 종료
        if (counter[maxIdx] - counter[minIdx] <= 1)
          break;

        // 덤프 실행 (예외 방지)
        if (maxIdx != minIdx) {
          counter[maxIdx]--;
          counter[minIdx]++;
        }
      }

      // 최종 높이 차이 계산 (OptionalInt 방지)
      int maxVal = Integer.MIN_VALUE;
      int minVal = Integer.MAX_VALUE;
      for (int h : counter) {
        if (h > maxVal)
          maxVal = h;
        if (h < minVal)
          minVal = h;
      }

      sb.append(maxVal - minVal).append("\n");
    }
    System.out.println(sb);
  }
}