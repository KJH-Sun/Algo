package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.function.Function;

public class BOJ_6198_옥상정원꾸미기 {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi.apply(br.readLine());
		long result = 0;
		int tmp;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			tmp = stoi.apply(br.readLine());
			while (!stack.isEmpty() && stack.peek() <= tmp) {
				stack.pop();
			}
			result += stack.size();
			stack.add(tmp);
		}
		System.out.println(result);
	}

}