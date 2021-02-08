import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {
	static int N;
	static boolean[] visit;
	static int[][] status;
	static Stack<Integer> stack = new Stack<>();
	static Stack<Integer> stack2 = new Stack<>();
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		status = new int[N][N];
		visit = new boolean[N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				status[i][j] = stoi(st.nextToken());
			}
		}
		dfs(0, 0);
		System.out.println(min);
	}

	private static void dfs(int depth, int index) {
		if (depth == N / 2) {
			diff();
			return;
		}

		for (int i = index; i < N; i++) {
			if (visit[i]) {
				continue;
			}
			visit[i] = true;
			dfs(depth + 1, i + 1);
			visit[i] = false;

		}
	}

	private static void diff() {
		int score = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (visit[i] && visit[j]) {
					score += status[i][j];
					score += status[j][i];
				}
				if (!visit[i] && !visit[j]) {
					score -= status[i][j];
					score -= status[j][i];
				}
			}
		}
		min = Math.min(min, Math.abs(score));

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
