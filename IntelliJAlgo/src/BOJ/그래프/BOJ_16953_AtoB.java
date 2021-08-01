import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_16953_AtoB {
	static BigInteger B;
	static int res = Integer.MAX_VALUE;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BigInteger A = new BigInteger(st.nextToken());
		B = new BigInteger(st.nextToken());
		solve(A, 0);
		System.out.println(res != Integer.MAX_VALUE ? res + 1 : -1);
	}

	private static void solve(BigInteger a, int cnt) {
		if (a.compareTo(B) == 1) {
			return;
		} else if (a.compareTo(B) == 0) {
			res = Math.min(cnt, res);
			return;
		}

		solve(a.multiply(new BigInteger("2")), cnt + 1);

		solve(new BigInteger(a.toString() + 1), cnt + 1);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
