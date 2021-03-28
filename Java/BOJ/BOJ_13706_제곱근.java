import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BOJ_13706_제곱근 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BigInteger N = new BigInteger(br.readLine());
		sqrt(N);
	}

	private static int sqrt(BigInteger n) { // n 목표 숫자
		BigInteger one = BigInteger.ONE;
		BigInteger two = new BigInteger("2");
		BigInteger left = BigInteger.ONE;
		BigInteger right = n;

		BigInteger mid, x;

		while (left.compareTo(right) <= 0) {
			mid = left.add(right).divide(two);
			x = mid.multiply(mid);
			if (x.compareTo(n) == 0) {
				System.out.println(mid.toString());
				break;
			} else if (x.compareTo(n) == -1) {
				left = mid.add(one);
			} else {
				right = mid.subtract(one);
			}
		}

		return 0;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
