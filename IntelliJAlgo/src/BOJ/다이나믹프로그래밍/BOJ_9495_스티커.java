package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;

public class BOJ_9495_스티커 {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int T, N, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = stoi.apply(br.readLine());
		StringTokenizer st;
		for (int tc = 0; tc < T; tc++) {
			N = stoi.apply(br.readLine());
			int[][] map = new int[2][N];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = stoi.apply(st.nextToken());
				}
			}
			int[][] dp = new int[2][N];
			dp[0][0] = map[0][0]; // 선택
			dp[1][0] = map[1][0]; // 선택
			dp[0][1] = dp[1][0] + map[0][1];
			dp[1][1] = dp[0][0] + map[1][1];

			for (int i = 2; i < N; i++) {
				dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + map[0][i];
				dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + map[1][i];
			}
			System.out.println(Math.max(dp[0][N - 1], dp[1][N - 1]));
		}
	}

}