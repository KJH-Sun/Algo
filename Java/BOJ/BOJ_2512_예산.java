import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2512_예산 {
	static int N;
	static int[] numArr;
	static int maxMoney = Integer.MIN_VALUE;
	static int UsedMax = Integer.MIN_VALUE;
	static int budget;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		numArr = new int[N];
		for (int i = 0; i < N; i++) {
			numArr[i] = stoi(st.nextToken());
			maxMoney = Math.max(maxMoney, numArr[i]);
		}
		budget = stoi(br.readLine());
		solve();
	}

	private static void solve() {
		int mid = 0;
		int bot = budget / N;
		int top = maxMoney;
		while (bot <= top) {
			mid = (bot + top) / 2;
			switch (check(mid)) {
			case 1:
				bot = mid + 1;
				break;
			case 2:
				top = mid - 1;
				break;
			}
		}
		System.out.println(top);
	}

	private static int check(int mid) {
		int sum = 0;
		for (int i : numArr) {
			if (i <= mid) {
				sum += i;
			} else {
				sum += mid;
			}
		}
		if (sum > budget) {
			return 2; // 합이 예산을 초과함(top을 내림)
		} else {
			return 1; // 합이 예산 미만(bot을 올림)
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
