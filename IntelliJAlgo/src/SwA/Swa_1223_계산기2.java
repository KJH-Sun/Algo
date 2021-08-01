import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Swa_1223_계산기2 {
	static Stack<Character> stack = new Stack<>();
	static Stack<Integer> result = new Stack<>();
	static int total;

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			StringBuffer sb = new StringBuffer();
			int T = stoi(br.readLine());
			String s = br.readLine();
			for (int i = 0; i < s.length(); i++) {

				if (s.charAt(i) == '*') { // *연산자 나온경우
					stack.push(s.charAt(i));

				} else if (s.charAt(i) == '+') { // '+' 문자인경우

					if (!stack.isEmpty()) { // 스택이 비어있지않고
						while (!stack.isEmpty()) { // 스택이 빌때까지 pop해서 보냄
							sb.append(stack.pop()); // 문자열에 집어넣음
						}
						stack.push(s.charAt(i)); // 스택에 + 집어넣음

					} else {
						stack.push(s.charAt(i)); // 스택에 아무것도 없거나, +만 있는 경우
					}

				} else {
					sb.append(s.charAt(i)); // 숫자인경우
				}
				if (i == s.length() - 1) {
					while (!stack.isEmpty()) { // 스택이 빌때까지 pop해서 보냄
						sb.append(stack.pop()); // 문자열에 집어넣음
					}
				}
			}
			String res = sb.toString(); // 후위연산자로 변환완료
			for (int i = 0; i < res.length(); i++) {
				if (res.charAt(i) == '*') {
					int x = result.pop();
					int y = result.pop();
					result.push(y * x); // NumericValue로 꺼내기때문에 +'0' 안해줘도 됨.
				} else if (res.charAt(i) == '+') {
					int x = result.pop();
					int y = result.pop();
					result.push(x + y);
				} else {
					result.push(Character.getNumericValue(res.charAt(i)));
				}
			}
			sb.setLength(0);
			sb.append("#" + tc + " " + result.get(0));
			System.out.println(sb);
			result.clear();
		}
	}
}
