import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_12904_A와B {
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String t = br.readLine();
		if (solve(s, t)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}

	}

	private static boolean solve(String s, String t) {
		StringBuffer sb = new StringBuffer();
		while (s.length() < t.length()) {
			switch (t.charAt(t.length() - 1)) {
			case 'A':
				t = t.substring(0, t.length() - 1);
				break;
			case 'B':
				t = t.substring(0, t.length() - 1);
				t = sb.append(t).reverse().toString();
				sb.setLength(0);
				break;
			}
		}
		return s.equals(t);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
