import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ_2869_달팽이는올라가고싶다 {
	static int A, B, V;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = stoi(st.nextToken());
		B = stoi(st.nextToken());
		V = stoi(st.nextToken());
		if (V == A) {
			System.out.println(1);
		} else if ((V - A) / (A - B) == 0) {
			System.out.println(2);
		} else if ((V - A) % (A - B) == 0) {
			System.out.println((V - A) / (A - B) + 1);
		} else {
			System.out.println((V - A) / (A - B) + 2);
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
