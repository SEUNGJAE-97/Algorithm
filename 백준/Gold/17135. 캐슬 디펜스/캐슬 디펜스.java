import java.io.*;
import java.util.*;

public class Main {
  private static int[][] matrix;
  private static int N, M, D, KILL;

  // 궁수 배치 조합
  private static void combination(int start, int depth, int[] archers) {
    if (depth == 3) {
      KILL = Math.max(KILL, simulate(archers));
      return;
    }
    for (int i = start; i < M; i++) {
      archers[depth] = i;
      combination(i + 1, depth + 1, archers);
    }
  }

  // 시뮬레이션 진행
  private static int simulate(int[] archers) {
    int[][] tempMatrix = new int[N][M];
    for (int i = 0; i < N; i++)
      tempMatrix[i] = matrix[i].clone();

    int killCount = 0;
    List<int[]> enemyList = new ArrayList<>();

    // 초기 적 위치 저장
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (tempMatrix[i][j] == 1) {
          enemyList.add(new int[] {i, j});
        }
      }
    }

    while (!enemyList.isEmpty()) {
      Set<String> targets = new HashSet<>();

      // 각 궁수가 공격할 적 찾기
      for (int archer : archers) {
        int[] target = findTarget(archer, enemyList);
        if (target != null) {
          targets.add(target[0] + "," + target[1]); // 좌표를 문자열로 저장
        }
      }

      // 적 제거
      killCount += targets.size();
      enemyList.removeIf(enemy -> targets.contains(enemy[0] + "," + enemy[1]));

      // 적 이동 (한 칸 아래로)
      enemyList.removeIf(enemy -> ++enemy[0] >= N);
    }

    return killCount;
  }

  // 궁수가 사정거리 내에서 가장 가까운 적 찾기
  private static int[] findTarget(int archerX, List<int[]> enemyList) {
    int minDist = D + 1;
    int targetX = -1, targetY = -1;

    for (int[] enemy : enemyList) {
      int ex = enemy[0], ey = enemy[1];
      int dist = Math.abs(N - ex) + Math.abs(archerX - ey); // 거리 계산

      if (dist <= D) {
        if (dist < minDist || (dist == minDist && ey < targetY)) {
          minDist = dist;
          targetX = ex;
          targetY = ey;
        }
      }
    }

    return targetX == -1 ? null : new int[] {targetX, targetY};
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] Data = br.readLine().split(" ");
    N = Integer.parseInt(Data[0]);
    M = Integer.parseInt(Data[1]);
    D = Integer.parseInt(Data[2]);
    matrix = new int[N][M];

    for (int i = 0; i < N; i++) {
      String[] split = br.readLine().trim().split(" ");
      for (int j = 0; j < M; j++) {
        matrix[i][j] = Integer.parseInt(split[j]);
      }
    }

    KILL = 0;
    combination(0, 0, new int[3]);
    System.out.println(KILL);
  }
}