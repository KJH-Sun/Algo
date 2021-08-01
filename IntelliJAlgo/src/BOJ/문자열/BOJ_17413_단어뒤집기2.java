import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ_17413_단어뒤집기2 {
	static Deque<Character> deq = new ArrayDeque<>();
	static boolean check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		String s = br.readLine();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '<') {
				check = true;
				while (!deq.isEmpty()) {
					sb.append(deq.pollLast());
				}
				deq.add(s.charAt(i));
			} else if (s.charAt(i) == '>') {
				check = false;
				while (!deq.isEmpty()) {
					sb.append(deq.pollFirst());
				}
				sb.append(s.charAt(i));
			} else if (s.charAt(i) == ' ' && !check) {
				while (!deq.isEmpty()) {
					sb.append(deq.pollLast());
				}
				sb.append(s.charAt(i));
			} else {
				deq.add(s.charAt(i));
			}
		}
		while (!deq.isEmpty()) {
			sb.append(deq.pollLast());
		}
		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
