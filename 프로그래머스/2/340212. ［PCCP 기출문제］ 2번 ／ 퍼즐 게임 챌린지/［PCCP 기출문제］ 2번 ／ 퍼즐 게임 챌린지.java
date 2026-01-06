class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100000;
        int answer = right;
        
        while(left <= right){
            int mid = (left + right)/2;
            long totalTime = calculateTime(diffs, times, mid);
            
            if(totalTime <= limit) {
                answer = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return answer;
    }
    
    private long calculateTime(int[] diffs, int[] times, int level){
        long total = 0;
        for(int i = 0; i < diffs.length; i++){
            int diff = diffs[i];
            int timeCur = times[i];
            if(diff <= level){
                total += timeCur;
            }else{
                int timePrev = (i == 0) ? 0 : times[i - 1];
                total += (long)(diff - level) * (timeCur + timePrev) + timeCur;
            }
        }
        return total;
    }
}