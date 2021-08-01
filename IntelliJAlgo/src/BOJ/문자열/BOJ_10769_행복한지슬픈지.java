import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_10769_행복한지슬픈지 {
	static int N, M;
	static String happy = ":-)";
	static String sad = ":-(";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int hcnt = KMP(s, happy);
		int scnt = KMP(s, sad);
		if (hcnt == 0 && scnt == 0) {
			System.out.println("none");
		} else if (hcnt == scnt) {
			System.out.println("unsure");
		} else if (hcnt > scnt) {
			System.out.println("happy");
		} else {
			System.out.println("sad");
		}
	}

	private static int KMP(String s, String p) {
		int[] pi = getPi(p);
		int cnt = 0;
		for (int i = 0, j = 0; i < s.length(); i++) {
			while (j > 0 && s.charAt(i) != p.charAt(j)) {
				j = pi[j - 1];
			}
			if (s.charAt(i) == p.charAt(j)) {
				if (j == p.length() - 1) {
					cnt++;
					j = pi[j - 1];
				} else {
					j++;
				}
			}
		}
		return cnt;
	}

	private static int[] getPi(String p) {
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
