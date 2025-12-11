import java.io.*;
import java.util.*;

public class Main {
	private static int E,M,S;
	
	public static class year{
		public year(int year, int earth, int sun, int moon) {
			this.year = year;
			this.Earth = earth;
			this.Sun = sun;
			this.Moon = moon;
		}
		int year;
		int Earth;
		int Sun;
		int Moon;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		E = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		year curYear = new year(1,1,1,1);
		
		while(true) {
			if(curYear.Earth == E && curYear.Sun == S && curYear.Moon == M) {
				System.out.println(curYear.year);
				break;
			}
			
			curYear.year++;
			curYear.Earth++;
			curYear.Sun++;
			curYear.Moon++;
			
			if (curYear.Earth > 15) {
				curYear.Earth = 1;
            }
            if (curYear.Sun > 28) {
            	curYear.Sun = 1;
            }
            if (curYear.Moon > 19) {
            	curYear.Moon = 1;
            }
			
		}
	}
}
