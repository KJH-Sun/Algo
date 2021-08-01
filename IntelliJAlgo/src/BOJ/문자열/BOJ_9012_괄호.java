import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;



/*
 * (가 들어오면 무조건 stack에 넣기
 * )가 들어온 경우, 스택이 비어있는지 여부를 확인
 * 비어있으면, VPS가 아니므로 NO를 출력하고 다음 테스트케이스로 이동
 * 비어있지 않은 경우, 스택에서 (를 하나 꺼냄
 * 모든 포문이 끝났을 때, 스택이 비어있지않으면( '(' 가 남아있으면) 짝이 맞지 않는 것이므로, VPS에 맞지 않으므로 NO출력
 * 비어있다면 모든 짝이 맞는것이므로 YES
 * 
 */
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
