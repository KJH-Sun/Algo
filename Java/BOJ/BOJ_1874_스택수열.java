import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1874_스택수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		Stack<Integer> stack = new Stack<>();
		Queue<Integer> que = new ArrayDeque<>();
		int N = stoi(br.readLine());
		for (int i = 1; i <= N; i++) {
			que.offer(i);
		}
		for (int i = 0; i < N; i++) {
			int now = stoi(br.readLine());
			if (!que.isEmpty()) {
				while (!que.isEmpty() && now >= que.peek()) {
					stack.add(que.poll());
					sb.append("+\n");
				}
			}
			if (stack.isEmpty()) {
				System.out.println("NO");
				System.exit(0);
			}
			if (now == stack.peek()) {
				sb.append("-\n");
				stack.pop();
			} else {
				System.out.println("NO");
				System.exit(0);
			}
		}
		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
