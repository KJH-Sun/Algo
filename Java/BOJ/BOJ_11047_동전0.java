import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_11047_동전0 {
	static int N, K;
	static int cnt;
	static Queue<Integer> que = new LinkedList<>();
	static Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		K = stoi(st.nextToken());
		for (int i = 0; i < N; i++) {
			que.offer(stoi(br.readLine()));
		}
		solve();
		System.out.println(cnt);
	}

	private static void solve() {
		while (!que.isEmpty()) {
			int x = que.poll();
			if (K / x > 0) {
				stack.push(x);
				continue;
			}
			break;
		}
		int change = K;
		while (!stack.isEmpty()) {
			int y = stack.pop();
			cnt += change / y;
			change %= y;
			if (change == 0) {
				break;
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
