import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1918_후위표기식 {
	static int T;
	static int[][] field;
	static Stack<Character> result = new Stack<>();
	static Stack<Character> oper = new Stack<>();
	static StringBuffer sb = new StringBuffer();
	static int bracket;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case '(':
				oper.push(s.charAt(i));
				break;
			case '-':
			case '+':
				if (!oper.isEmpty()) {
					while (!oper.isEmpty()) {
						if (oper.peek() == '(') {
							break;
						}
						result.push(oper.pop());
					}
					oper.push(s.charAt(i));
				} else {
					oper.push(s.charAt(i));
				}
				break;
			case '*':
			case '/':
				if (!oper.isEmpty()) { // 연산자 스택이 비어있지 않으면
					if (oper.peek() == '+' || oper.peek() == '-') { // 들어있는게 +나 -이면
						oper.push(s.charAt(i));
					} else { // 들어있던 것이 * 나 / 이면
						while (!oper.isEmpty()) {
							if (oper.peek() == '(') {
								break;
							}
							if (oper.peek() == '+' || oper.peek() == '-') {
								break;
							}
							result.push(oper.pop());
						}
						oper.push(s.charAt(i));
					}
				} else { // 연산자 스택이 비어있으면
					oper.push(s.charAt(i));
				}
				break;
			case ')':
				while (!oper.isEmpty()) {
					if (oper.peek() == '(') {
						oper.pop();
						break;
					}
					result.push(oper.pop());
				}
				oper.push(s.charAt(i));
				break;
			default:
				result.push(s.charAt(i));
				break;
			}
		}
		while (!oper.isEmpty()) {
			result.push(oper.pop());
		}
		for (char c : result) {
			if (c == ')' || c == '(') {
				continue;
			}
			System.out.print(c);
		}
	}

//	private static int stoi(String s) {
//		return Integer.parseInt(s);
//	}

}
