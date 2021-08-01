import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;


/*
 * dp 배열 : 자기 숫자까지 만들어질 수 있는 최장 증가 수열
 * 두개의 포문으로, 두번째 포문에서 자기 이전까지의 dp배열을 확인하면서 만약 그 숫자에 해당하는 arr가 자신보다 작다면,
 * 자신의 dp배열은 그 arr[j]의 dp[j]+1이 된다.
 * 최종적으로 dp배열에서 가장 큰 값을 출력한다.
 * 
 */

public class BOJ_11053_가장긴증가하는부분수열 {
	static int N;
	static int[] dp;
	static int[] arr;
	static int cnt;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N+1];
		dp = new int[N+1];
		for (int i = 0; i < N; i++) {
			arr[i] = (stoi(st.nextToken()));
		}

		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j] && dp[j] + 1 > dp[i])
					dp[i] = dp[j] + 1;
			}
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
