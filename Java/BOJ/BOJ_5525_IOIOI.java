import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_5525_IOIOI {
	static int N, M;
	static String p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		M = stoi(br.readLine());
		String s = br.readLine();
		System.out.println(KMP(s));
	}

	private static int KMP(String s) {
		int[] pi = getPi();
		int cnt = 0;
		for (int i = 0, j = 0; i < s.length(); i++) {
			while (j > 0 && p.charAt(j) != s.charAt(i)) {
				j = pi[j - 1];
			}
			if (p.charAt(j) == s.charAt(i)) {
				if (j == p.length() - 1) {
					cnt++;
					j=pi[j];
				} else {
					j++;
				}
			}
		}
		return cnt;
	}

	private static int[] getPi() {
		StringBuilder sb = new StringBuilder();
		sb.append("I");
		for (int i = 0; i < N; i++) {
			sb.append("OI");
		}
		p = sb.toString();
		int[] pi = new int[p.length()];
		
		for (int i = 1, j = 0; i < p.length(); i++) {
			while (j > 0 && p.charAt(i) != p.charAt(j)) {
				j = pi[j - 1];
			}
			if (p.charAt(i) == p.charAt(j)) {
				pi[i] = ++j;
			}
		}

		return pi;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
