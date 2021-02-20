
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1927_최소힙 {
	static int N, five, three;
	static PriorityQueue<Integer> pQ = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		for (int i = 0; i < N; i++) {
			int x = stoi(br.readLine());
			if (x == 0) {
				if (!pQ.isEmpty()) {
					System.out.println(pQ.poll());
				} else {
					System.out.println(0);
				}
			} else {
				pQ.offer(x);
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
