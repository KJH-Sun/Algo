import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swa_5215_햄버거 {
	static int N, L;
	static int[] score;
	static int[] calo;
	static int max;

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			max = Integer.MIN_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			L = stoi(st.nextToken());
			score = new int[N];
			calo = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				score[i] = stoi(st.nextToken());
				calo[i] = stoi(st.nextToken());
			}
			solve(0, 0, 0);
			System.out.printf("#%d %d\n", tc, max);

		}

	}

	private static void solve(int pos, int tcal, int tscore) {
		if (pos == N) {
			if (tcal <= L) {
				max = Math.max(max, tscore);
				return;
			}
			return;
		}
		solve(pos + 1, tcal + calo[pos], tscore + score[pos]);
		solve(pos + 1, tcal, tscore);
	}
}
