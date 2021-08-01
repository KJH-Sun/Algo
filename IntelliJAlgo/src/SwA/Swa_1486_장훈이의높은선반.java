import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swa_1486_장훈이의높은선반 {
	static int T, N, B;
	static int[] numArr;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			res = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			B = stoi(st.nextToken());
			numArr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				numArr[i] = stoi(st.nextToken());
			}
			solve(0, 0);
			sb.append("#" + tc + " " + res + "\n");
		}
		System.out.println(sb);
	}

	private static void solve(int pos, int total) {
		if (pos == N) {
			if (total >= B) {
				res = Math.min(res, Math.abs(B - total));
			}
			return;
		}

		solve(pos + 1, total + numArr[pos]);
		solve(pos + 1, total);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
