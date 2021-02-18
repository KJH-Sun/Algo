import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_9012_괄호 {
	static int T;
	static int[][] field;
	static Stack<Character> stack = new Stack<>();
	static StringBuffer sb = new StringBuffer();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = stoi(st.nextToken());
		testcase: for (int tc = 0; tc < T; tc++) {
			String s = br.readLine();
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '(') {
					stack.push(s.charAt(i));
				} else {
					if (!stack.isEmpty()) {
						stack.pop();
					} else {
						sb.append("NO" + "\n");
						continue testcase;
					}
				}
			}
			if (!stack.isEmpty()) {
				sb.append("NO" + "\n");
			} else {
				sb.append("YES" + "\n");
			}
			stack.clear();
		}

		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
