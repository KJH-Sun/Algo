import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 * 수들의 합 2와 같은 방식으로 부분수열의 합 구함
 * 단, sum>=S일때 수열의 길이를 Math.min으로 체크해 계속 min에 넣어주었음.
 * 만약 min이 초기값 그대로이거나, 합이 0이라면 0을 출력
 * 이외의 경우 min을 출력
 * 
 */


public class BOJ_1806_부분합 {
	static int N, S;
	static int[] numArr;
	static boolean[] visit;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		S = stoi(st.nextToken());
		numArr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numArr[i] = stoi(st.nextToken());
		}
		solve();
		if (min == Integer.MAX_VALUE || S == 0) {
			System.out.println(0);
		} else {
			System.out.println(min);
		}
	}

	private static void solve() {
		int start = 0;
		int end = 0;
		int sum = numArr[0];
		while (start <= end && end < N) {
			if (sum < S) {
				sum += numArr[++end];
			} else if (sum >= S) {
				min = Math.min(min, end - start + 1);
				sum -= numArr[start++];
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
