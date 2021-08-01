import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 용돈관리와 거의 같은 방식으로 진행한다.
 * bot은 예산을 N으로 나눈 수, top은 신청예산 중 가장 높은 수로 정한 이후 이분탐색 시작
 * 합이 예산을 초과하면 top을 미드로 내리고, 그렇지않으면 bot을 중간으로 올림
 * 최댓값을 구하므로 top을 return
 */


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
