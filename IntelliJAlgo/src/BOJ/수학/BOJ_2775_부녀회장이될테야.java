import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ_2775_부녀회장이될테야 {
	static int T, K, N;
	static int[][] hotel;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = stoi(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			K = stoi(br.readLine());
			N = stoi(br.readLine());
			System.out.println(solve(K, N));
		}

	}

	private static int solve(int floor, int room) {
		if (floor == 1) {
			int sum = 0;
			for (int i = 1; i <= room; i++) {
				sum += i;
			}
			return sum;
		}

		int sum = 0;
		for (int i = 1; i <= room; i++) {
			sum += solve(floor - 1, i);
		}

		return sum;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
