import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Swa_1218_괄호짝짓기 {
	static int N;
	static char[] L = { '(', '{', '[', '<' };
	static char[] R = { ')', '}', ']', '>' };
	static boolean[] red = new boolean[4];
	static Stack<Character> stack = new Stack<>();

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	private static int check(String s) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 4; j++) {
				if (s.charAt(i) == L[j]) {
					stack.push(s.charAt(i));
					break;
				}
				if (s.charAt(i) == R[j]) {
					if (stack.peek() == L[j]) {
						stack.pop();
						break;
					}
					return 0;
				}
			}
		}
		return 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			N = stoi(br.readLine());
			String s = br.readLine();

			System.out.printf("#%d %d\n", tc, check(s));
			stack.clear();
		}
	}

}
