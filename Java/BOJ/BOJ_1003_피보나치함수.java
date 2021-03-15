import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/*
 * 메모리제이션을 통해서 특정 피보나치 연산 solve(n)에 호출되는 solve(1)과 solve(0)의 값을 memo에 저장해놓은 후 나중에 solve(n)이 또 호출되면 
 * memo(n)을 바로 리턴
 */

public class BOJ_1003_피보나치함수 {
	static int T;
	static int[][] map;
	static int[][] memo = new int[41][2];
	static boolean[] visit = new boolean[41];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = stoi(br.readLine());
		memo[0][0] = 1;
		memo[1][1] = 1;
		for (int tc = 0; tc < T; tc++) {
			int[] res = solve(stoi(br.readLine()));
			System.out.printf("%d %d\n", res[0], res[1]);
		}

	}

	private static int[] solve(int num) {
		if (num == 1) {
			return new int[] { 0, 1 };
		}
		if (num == 0) {
			return new int[] { 1, 0 };
		}
		if (!visit[num]) {
			int[] a = solve(num - 1);
			int[] b = solve(num - 2);
			int[] sum = new int[2];
			sum[0] = a[0] + b[0];
			sum[1] = a[1] + b[1];
			memo[num][0] = sum[0];
			memo[num][1] = sum[1];
			visit[num] = true;
			return sum;
		} else {
			return memo[num];
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
