import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2003_수들의합2 {
	static int N, M, X;
	static int[] numArr;
	static boolean[] visit;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		numArr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numArr[i] = stoi(st.nextToken());
		}
		solve();
		System.out.println(res);
	}

	private static void solve() {
		int start = 0;
		int end = 0;
		int sum = numArr[0];
		while (true) {
			if (sum == M) {
				res++;
				sum -= numArr[start++];
				continue;
			}
			if (sum > M || end == N - 1) {
				if (start >= end && end == N - 1) {
					break;
				}
				sum -= numArr[start++];
				continue;
			}
			if (sum < M) {
				sum += numArr[++end];
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
