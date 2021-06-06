import java.io.IOException;
import java.util.Stack;

/*
 * 시뮬레이션 문제
 */


public class 신규아이디추천 {
	static String valid = "abcdefghijklmnopqrstuvwxyz0123456789-_.";

	public static void main(String[] args) throws IOException {
//		String new_id = "...!@BaT#*..y.abcdefghijklm";
		String new_id = "abcdefghijklmn.p";
		System.out.println(solution(new_id));
	}

	public static String solution(String new_id) {
		// 1번
		String res = new_id.toLowerCase();

		// 2번
		String ans = "";
		for (int i = 0; i < res.length(); i++) {
			if (valid.indexOf(res.charAt(i)) < 0) {
				continue;
			}
			ans += res.charAt(i);
		}

		// 3번
		Stack<Character> stack = new Stack<>();
		stack.add(ans.charAt(0));
		for (int i = 1; i < ans.length(); i++) {
			if (stack.peek() == '.' && ans.charAt(i) == '.') {
				continue;
			}
			stack.add(ans.charAt(i));
		}

		// 4번
		if (!stack.isEmpty()) {
			if (stack.firstElement() == '.') {
				stack.remove(0);
			}

		}
		if (!stack.isEmpty()) {
			if (stack.peek() == '.') {
				stack.pop();
			}
		}

		// 5번
		if (stack.isEmpty()) {
			stack.add('a');
		}

		// 6번
		if (stack.size() >= 16) {
			while (stack.size() != 15) {
				stack.pop();
			}
			if (stack.peek() == '.') {
				stack.pop();
			}
		}
		// 7번
		if (stack.size() <= 2) {
			char c = stack.peek();
			while (stack.size() != 3) {
				stack.add(c);
			}
		}

		ans = "";
		for (char c : stack) {
			ans += c;
		}

		return ans;
	}

}
