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


public class BOJ_9095_123더하기 {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int T, N, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = stoi.apply(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			cnt = 0;
			N = stoi.apply(br.readLine());
			dp(N);
			System.out.println(cnt);
		}
	}

	private static void dp(int N) {
		if (N == 0) {
			cnt++;
			return;
		}

		if (N >= 3) {
			dp(N - 3);
		}
		if (N >= 2) {
			dp(N - 2);
		}
		if (N >= 1) {
			dp(N - 1);
		}
	}

}