import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2961_도영이가만든맛있는음식 {
	static int N;
	static int[][] field;
	static StringBuffer sb = new StringBuffer();
	static int[] sour;
	static int[] bitter;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = stoi(br.readLine());
		sour = new int[N];
		bitter = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			sour[i] = stoi(st.nextToken());
			bitter[i] = stoi(st.nextToken());
		}
		comb(0, 0, 1, 0);
		System.out.println(min);
	}

	private static void comb(int pos, int cnt, int so, int bi) {
		if (pos == N) {
			if (cnt == 0) {
				return;
			}
			min = Math.min(min, Math.abs(so - bi));
			return;
		}
		comb(pos + 1, cnt + 1, so * sour[pos], bi + bitter[pos]);
		comb(pos + 1, cnt, so, bi);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
