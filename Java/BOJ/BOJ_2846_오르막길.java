import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 증가하는 부분 최장수열을 구하는 문제
 */


public class BOJ_2846_오르막길 {
	static int N, dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		dp = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		dp[0] = 0;
		int pre = stoi(st.nextToken());
		for (int i = 1; i < N; i++) {
			int now = stoi(st.nextToken());
			if (pre < now) {
				dp[i] = now - pre + dp[i - 1];
			}
			pre = now;
		}
		System.out.println(Arrays.stream(dp).max().getAsInt());
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
