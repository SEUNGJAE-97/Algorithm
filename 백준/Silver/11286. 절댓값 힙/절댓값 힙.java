import java.util.*;
import java.io.*;

public class Main {
	static int N;

	public static void main(String[] args) throws Exception {
		Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				int absMax =  Integer.compare(Math.abs(o1), Math.abs(o2));
				if(absMax != 0 ) {
					return absMax;
				}
				return Integer.compare(o1, o2);
			}
		});
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());

			if (x != 0)
				queue.offer(x);
			else if (x == 0 && !queue.isEmpty())
				System.out.println(queue.poll());
			else if (queue.isEmpty())
				System.out.println(0);
		}
	}
}