import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 1019번 책페이지랑 비슷한 문제
 * 
 * 
 */

public class Swa_5604_구간합 {
	static int T;
	static long A, B;
	static long ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
			ans = 0;
			long mul = 1;
			while (A <= B) {
				while (A % 10 != 0 && A <= B) { // 자리수별로 세기, +1해주면서 0될때까지 센다.
					check(A, mul);
					A++;
				}
				if (A > B || (A == 0 && B == 0)) {
					break;
				}
				while (B % 10 != 9 && A <= B) {
					check(B, mul);
					B--;
				}
				A /= 10; // 자리수 변경
				B /= 10;
				long m = (B - A + 1) * mul;
				for (int i = 0; i < 10; i++) { // 자리수가 10이라면 start/10-end/10 +1 번 * 10번만큼 숫자가 나오기 때문
					ans += m * i;
				}
				mul *= 10;
			}
			System.out.println("#" + tc + " " + ans);
		}
	}

	private static void check(long n, long d) {
		while (n > 0) {
			ans += (n % 10) * d; // 토탈값 구하는 것이므로 그 자리수 * mul값을 더해준다.(만약 10의 자리였다면,
			n /= 10;
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
