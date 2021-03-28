import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class BOJ_17103_골드바흐의파티션 {
	static int[] arr;
	static boolean[] demi = new boolean[1000001];
	static final int MAXINT = 1000001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = stoi(br.readLine());
		arr = new int[T];
		for (int tc = 0; tc < T; tc++) {
			arr[tc] = stoi(br.readLine());
		}
		eratos();
		for (int i = 0; i < T; i++) {
			System.out.println(GB(arr[i]));
		}
	}

	private static int GB(int n) {
		int cnt = 0;
		for (int i = 2; i <= n / 2; i++) {
			if (!demi[i] && !demi[n - i]) {
				cnt++;
			}
		}
		return cnt;
	}

	private static void eratos() { // false이면 소수
		demi[0] = true;
		demi[1] = true;
		for (int i = 2; i * i < MAXINT; i++) {
			for (int j = i * i; j < MAXINT; j += i) {
				demi[j] = true;
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
