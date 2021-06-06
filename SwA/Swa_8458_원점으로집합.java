import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swa_8458_원점으로집합 {
	static int T, N;
	static long[] arr;
	static long max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = stoi(br.readLine());
			arr = new long[N];
			boolean even = false;
			boolean odd = false;
			int res = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i] = Math.abs(stoi(st.nextToken())) + Math.abs(stoi(st.nextToken()));
				if (arr[i] % 2 == 0) {
					even = true;
				} else {
					odd = true;
				}
			}
			if (!even || !odd) {
				max = Arrays.stream(arr).max().getAsLong();
				for (res = 0; res < 1000000; res++) {
					max -= res;
					if (max <= 0) {
						break;
					}
				}
				max = (max * -1) % 2;
				if (max != 0) {
					if (max == (res + 1) % 2) {
						res += 1;
					} else {
						res += 2;
					}
				}
			} else {
				res = -1;
			}
			System.out.println("#" + tc + " " + res);

		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
