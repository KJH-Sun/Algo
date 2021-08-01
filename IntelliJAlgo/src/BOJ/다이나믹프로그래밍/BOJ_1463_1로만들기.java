import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/*
 * 숫자를 받았을때, 그 숫자는 6으로 나눠지는가, 3으로 나눠지는가, 2로 나눠지는가, -1 연산밖에 적용할 수 없는가의 네가지 경우의 수가 있다.
 * 6으로 나눠지는 경우, 고려해야하는 경우의 수는 3으로 나눠보는 것과 2로 나누는 것이다
 * 3으로 나눠지는 경우, 고려해야하는 경우의 수는 3으로 나누는 것과 1을 빼는 것이다
 * 2로 나눠지는 경우, 고려해야하는 경우의 수는 2로 나누는 것과 1을 뺴는 것이다
 * 나눠지지 않는 경우, 1을 빼는 선택지밖에 없다.
 * 즉, solve 자체를 플랫하게 접근해서 보면, solve라는 함수는 그 수가 가장 짧게 1에 도달하는 연산의 횟수를 리턴해주는 함수이다.
 * 두가지 경우의 수를 모두 solve 돌려서 그중에서 작은 값을 Math min 으로 받아 그것에 1을 더하고 dp[num]에 넣어서 리턴해주면 끝
 */

public class BOJ_1463_1로만들기 {
	static int N;
	static int[] dp;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		dp = new int[N + 1];
		System.out.println(solve(N));

	}

	private static int solve(int num) {
		if (num == 1) {
			return 0;
		}
		if (dp[num] != 0) {
			return dp[num];
		}
		if (num % 6 == 0) {
			return dp[num] = Math.min(solve(num / 3), solve(num / 2)) + 1;
		} else if (num % 3 == 0) {
			return dp[num] = Math.min(solve(num / 3), solve(num - 1)) + 1;
		} else if (num % 2 == 0) {
			return dp[num] = Math.min(solve(num / 2), solve(num - 1)) + 1;
		} else {
			return dp[num] = solve(num - 1) + 1;
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
