import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ_1011_FlymetotheAlphaCentauri {
	static int T, X, Y;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			X = stoi(st.nextToken());
			Y = stoi(st.nextToken());
			int N = (int) Math.sqrt(Y - X);
			if ((Y - X) > N * (N + 1)) {
				N++;
			}
			if ((long)(N - 1) * N + N >= (Y - X)) {
				System.out.println(2 * N - 1);
			} else {
				System.out.println(2 * N);
			}

		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
