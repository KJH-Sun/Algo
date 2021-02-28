import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_3985_롤케이크 {
	static int L, N;
	static int[] cake;
	static int[] cnt;
	static int maxWant;
	static int maxWantNum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		L = stoi(br.readLine());
		N = stoi(br.readLine());
		cake = new int[L + 1];
		cnt = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = stoi(st.nextToken());
			int e = stoi(st.nextToken());
			if (e - s > maxWant) {
				maxWant = e - s;
				maxWantNum = i;
			}
			for (int j = s; j <= e; j++) {
				if (cake[j] == 0) {
					cake[j] = i;
					cnt[i]++;
				}
			}
		}
		System.out.println(maxWantNum);
		for (int i = 1; i <= N; i++) {
			if (cnt[i] == Arrays.stream(cnt).max().getAsInt()) {
				System.out.println(i);
				System.exit(0);
			}
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
