package combination;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2798_블랙잭 {
	static int N, M, arr[];
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = stoi(st.nextToken());
		}
		solve(0, 0, 0);
		System.out.println(max);
	}

	private static void solve(int pos, int cnt, int sum) {
		if (sum > M || cnt > 3) {
			return;
		}
		if (pos == N) {
			max = Math.max(sum, max);
			return;
		}

		solve(pos + 1, cnt + 1, sum + arr[pos]);
		solve(pos + 1, cnt, sum);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
