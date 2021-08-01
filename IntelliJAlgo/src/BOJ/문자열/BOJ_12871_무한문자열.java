
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_12871_무한문자열 {
	static String s, t;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();
		t = br.readLine();
		if (check(s.length() * t.length())) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	private static boolean check(int dL) {
		String ns = "";
		String nt = "";
		for (int i = 0; i < t.length(); i++) {
			ns += s;
		}
		for (int i = 0; i < s.length(); i++) {
			nt += t;
		}

		return ns.equals(nt);

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
