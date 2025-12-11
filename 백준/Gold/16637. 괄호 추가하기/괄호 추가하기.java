import java.util.*;
import java.io.*;

public class Main {

	private static int N;
	private static long maxValue = Long.MIN_VALUE;
	private static String expression;
	
	private static long calculate(long a, char op, long b) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        }
        return 0;
    }
	
	private static void dfs(int opIdx, Long curResult) {
		if (opIdx >= N) { 
            maxValue = Math.max(maxValue, curResult);
            return;
        }
		
		long nextNum = Character.getNumericValue(expression.charAt(opIdx + 1));
        char op = expression.charAt(opIdx);
        long resultNoBracket = calculate(curResult, op, nextNum);
        
        dfs(opIdx + 2, resultNoBracket);
        
        if (opIdx + 2 < N) {
            long num1 = nextNum;
            char opBracket = expression.charAt(opIdx + 2);
            long num2 = Character.getNumericValue(expression.charAt(opIdx + 3));
            long bracketResult = calculate(num1, opBracket, num2);
            long resultWithBracket = calculate(curResult, op, bracketResult);
       
            dfs(opIdx + 4, resultWithBracket);
        }
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
        expression = br.readLine();
        if (N == 1) {
            System.out.println(Character.getNumericValue(expression.charAt(0)));
            return;
        }
        long initialValue = Character.getNumericValue(expression.charAt(0));
        
        dfs(1, initialValue); 

        System.out.println(maxValue);
	}

}
