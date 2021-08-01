import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_10845_큐 {
	static Deque<Integer> deq = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		while (true) {
			String s = br.readLine();
			if (s == null || s.length() == 0) {
				break;
			}
			st = new StringTokenizer(s);
			String order = st.nextToken();
			switch (order) {
			case "push":
				deq.add(stoi(st.nextToken()));
				break;
			case "front":
				System.out.println(deq.peekFirst() == null ? -1 : deq.peekFirst());
				break;
			case "back":
				System.out.println(deq.peekLast() == null ? -1 : deq.peekLast());
				break;
			case "size":
				System.out.println(deq.size());
				break;
			case "pop":
				System.out.println(deq.isEmpty() ? -1 : deq.poll());
				break;
			case "empty":
				System.out.println(deq.isEmpty() ? 1 : 0);
				break;
			}
		}
		System.out.println(sb);

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
