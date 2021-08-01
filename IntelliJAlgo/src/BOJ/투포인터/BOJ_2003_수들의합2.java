import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 * sum이 구하는 숫자 M보다 크다면 start를 밀고, 작다면  end를 민다.
 * start와 end둘 다 0에서 시작하여, end가 도달하는 시점에 numArr[end]를 sum에 더하고
 * start가 도달하는 시점에 numArr[start]를 sum에서 빼는식으로 연속된 수열의 합을 구한다.
 * 둘 다 끝에 도달하면 종료
 * 
 */


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
