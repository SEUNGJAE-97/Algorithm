import java.util.*;

class Solution {
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, -1, 0, 1};
    
    public class Robot{
        int curR, curC; 
        int targetIdx; 
        int[] route;   
        boolean arrived; 
        
        public Robot(int[] route, int[][] points) {
            this.route = route;
            int startPointIdx = route[0] - 1;
            this.curR = points[startPointIdx][0];
            this.curC = points[startPointIdx][1];
            this.targetIdx = 1; 
            this.arrived = false;
        }
        public void move(int[][] points) {
        if (arrived) return;

        int targetPointIdx = route[targetIdx] - 1;
        int targetR = points[targetPointIdx][0];
        int targetC = points[targetPointIdx][1];

        if (curR != targetR) {
            if (curR < targetR) curR++;
            else curR--;
        } 
        else if (curC != targetC) {
            if (curC < targetC) curC++;
            else curC--;
        }

        if (curR == targetR && curC == targetC) {
            targetIdx++; 
            if (targetIdx == route.length) {
                arrived = true; 
            }
        }
    }
    }
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        List<Robot> robots = new ArrayList<>();
        
        for (int i = 0; i< routes.length; i++){
            robots.add(new Robot(routes[i], points));
        }
        while(true){
            Map<String, Integer> map = new HashMap<>();
            int finishedCount = 0;
            
            for(Robot r : robots){
                if(r.arrived && r.targetIdx == -1){
                    finishedCount++;
                    continue;
                }
                String pos = r.curR + "," + r.curC;
                map.put(pos, map.getOrDefault(pos,0)+1);
            }
            
            if(finishedCount == robots.size()) break;
            for(int count : map.values()){
                if(count >= 2) answer++;
            }
            for(Robot r:robots){
                if(r.arrived){
                    r.targetIdx = -1;
                    continue;
                }
                r.move(points);
            }
        }
        return answer;
    }
}