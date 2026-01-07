class Solution {
    	public int solution(int[] bandage, int health, int[][] attacks) {
        int t = bandage[0];// 시전시간 
        int x = bandage[1];// 초당 회복력     
        int y = bandage[2];// 추가 회복량 
        int answer = health;
        int count = 0;
        
        for(int[] attack : attacks) {
        	int turn = attack[0];
        	int damage= attack[1];
        	
        	//1 체력 회복 
        	int duration = turn - count - 1; 
            if (duration > 0) {
                int recovery = (duration * x) + (duration / t * y);
                answer = Math.min(health, answer + recovery);
            }
        	//2 데미지처리 
        	answer -= damage;
        	if(answer<=0) return -1;
        	
        	count = turn;
        }
        return answer;
    }
}