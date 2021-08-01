import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 들어온 문자가 ( 인경우에는 무조건 연산자 Stack에 push
 * -나 +인 경우에는, 
 * 연산자 Stack이 비어있는 경우에는 push
 * 연산자 스택이 비어있지 않은 경우, (가 나오거나 연산자스택이 빌때까지 pop하여 result 스택에 push
 * 
 * *나 / 인 경우에는
 * 연산자 스택이 비어있으면 push
 * 비어있지 않으면, 연산자 스택의 가장 위를 확인하여 그것이 +나 -이면 연산자 스택에 push
 * 들어있던것이 *나 / 이면, 연산자 스택이 비거나 or (가 나오거나 or +나 -가 나올때까지 pop하여 result 스택에 push한다.
 * 이후 연산자 스택에 push
 * )가 들어온 경우
 * (가 나올떄까지 연산자 스택에서 result 스택으로 push한다
 * 최종적으로 괄호를
 */

public class BOJ_1918_후위표기식 {
	static int T;
	static int[][] field;
	static Stack<Character> result = new Stack<>();
	static Stack<Character> oper = new Stack<>();
	static StringBuffer sb = new StringBuffer();

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

}
