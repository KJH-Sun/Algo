import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149_RGB거리 {
	static int[][] price;
	static int[][] dp;
	static int N;
	static int minP = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		price = new int[N][3];
		dp = new int[N][3];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				price[i][j] = stoi(st.nextToken());
			}
		}
		dp[0][0] = price[0][0];
		dp[0][1] = price[0][1];
		dp[0][2] = price[0][2];
		System.out.println(solve(N, -1));

	}

	private static int solve(int pos, int overlap) {
		if (pos == N) {
			return Math.min(solve(pos - 1, 0), Math.min(solve(pos - 1, 1), solve(pos - 1, 2)));
		}
		switch (overlap) {
		case 0: // R
			return dp[pos][overlap] = dp[pos][overlap] != 0 ? dp[pos][overlap]
					: Math.min(solve(pos - 1, 1), solve(pos - 1, 2)) + price[pos][overlap];
		case 1: // G
			return dp[pos][overlap] = dp[pos][overlap] != 0 ? dp[pos][overlap]
					: Math.min(solve(pos - 1, 0), solve(pos - 1, 2)) + price[pos][overlap];
		case 2: // B
			return dp[pos][overlap] = dp[pos][overlap] != 0 ? dp[pos][overlap]
					: Math.min(solve(pos - 1, 0), solve(pos - 1, 1)) + price[pos][overlap];
		}
		return dp[pos][overlap];
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
