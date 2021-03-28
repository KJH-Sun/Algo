import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_16922_로마숫자만들기 {
	static int N;
	static int[] arr = { 1, 5, 10, 50 };
	static Set<Integer> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		comb(0, 0, 0);
		System.out.println(set.size());
	}

	/*
	 * solve(sum+1)
	 * solve(sum+5)
	 * solve(sum+50)
	 * solve(sum+10)
	 * 이렇게 접근하면 n^4승이지만
	 * 아래와 같이 접근하면 4^n승이므로 아래의 코드는 시간초과 안남
	 */
	private static void comb(int pos, int cnt, int sum) {
		if (pos == 4) {
			return;
		}
		if (cnt == N) {
			set.add(sum);
			return;
		}
		comb(pos, cnt + 1, sum + arr[pos]);
		comb(pos + 1, cnt + 1, sum + arr[pos]);
		comb(pos + 1, cnt, sum);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
