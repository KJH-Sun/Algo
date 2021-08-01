import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_1786_찾기 {
	static String str;
	static String pattern;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		str = br.readLine();
		pattern = br.readLine();
		ArrayList<Integer> res = KMP();
		System.out.println(res.size());
		for (int i : res) {
			System.out.print(i + 1 + " ");
		}
	}

	private static ArrayList<Integer> KMP() {
		int[] pi = getPi();
		ArrayList<Integer> res = new ArrayList<Integer>();
		int j = 0;
		for (int i = 0; i < str.length(); i++) {
			while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}

			if (str.charAt(i) == pattern.charAt(j)) {
				if (j == pattern.length() - 1) {
					res.add(i - (pattern.length() - 1));
					j = pi[j];
				} else {
					j++;
				}
			}
		}

		return res;
	}

	private static int[] getPi() {
		int[] pi = new int[pattern.length()];
		int j = 0;
		for (int i = 1; i < pattern.length(); i++) {
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			if (pattern.charAt(i) == pattern.charAt(j)) {
				pi[i] = ++j;
			}
		}
		return pi;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
