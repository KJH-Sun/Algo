import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swa_5607_조합 {
	static int T;
	private static final int M = 1234567891;
	static long[] f;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = stoi(br.readLine());
		fac();
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = stoi(st.nextToken());
			int R = stoi(st.nextToken());
			long b1 = (f[R] * f[N - R]) % M;
			long b2 = solve(b1, M - 2);
			System.out.println("#" + tc + " " + (f[N] * b2) % M);
		}
	}

	private static void fac() { // 팩토리얼 미리 구해놓기
		f = new long[1000001];
		f[0] = 1;
		for (int i = 1; i < 1000001; i++) {
			f[i] = f[i - 1] * i;
			f[i] %= M;
		}
	}

	private static long solve(long n, int x) { // 페르마의 소정리
		if (x == 0)
			return 1;
		long tmp = solve(n, x / 2);
		long ret = (tmp * tmp) % M;
		if (x % 2 == 0)
			return ret;
		else
			return (ret * n) % M;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
