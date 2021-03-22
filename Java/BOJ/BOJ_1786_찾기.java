import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_1786_찾기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String T = br.readLine();
		String P = br.readLine();
		ArrayList<Integer> list = KMP(T, P);
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + 1 + " ");
		}

	}

	public static int[] getPi(String p) {
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

	private static ArrayList<Integer> KMP(String s, String p) {
		ArrayList<Integer> list = new ArrayList<>();
		int[] pi = getPi(p);
		for (int i = 0, j = 0; i < s.length(); i++) {
			while (j > 0 && s.charAt(i) != p.charAt(j)) {
				j = pi[j - 1];
			}
			if (s.charAt(i) == p.charAt(j)) {
				if (j == p.length() - 1) {
					list.add(i - p.length() + 1);
					j = pi[j];
				} else {
					j++;
				}
			}
		}
		return list;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
