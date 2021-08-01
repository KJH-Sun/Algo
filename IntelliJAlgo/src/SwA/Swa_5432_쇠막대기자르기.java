import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Swa_5432_쇠막대기자르기 {
	static Stack<Character> stack = new Stack<>();
	static int total;

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String s = br.readLine();
			total = 0;
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '(') {
					stack.push(s.charAt(i));
				} else {
					if (s.charAt(i - 1) == '(') {
						stack.pop();
						total += stack.size();
					} else {
						total += 1;
						stack.pop();
					}
				}
			}
			sb.append("#" + tc + " " + total);
			sb.append("\n");
			stack.clear();
		}
		System.out.println(sb);
	}
}
