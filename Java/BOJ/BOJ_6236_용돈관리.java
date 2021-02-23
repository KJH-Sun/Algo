import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6236_용돈관리 {
	static int N, M;
	static int[] numArr;
	static int maxMoney = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		numArr = new int[N];
		for (int i = 0; i < N; i++) {
			numArr[i] = stoi(br.readLine());
			maxMoney = Math.max(maxMoney, numArr[i]);
		}
		solve();
	}

	private static void solve() {
		int bot = 1;
		int top = maxMoney * M;
		while (bot <= top) {
			int mid = (bot + top) / 2;
			switch (check(mid)) {
			case 0: // 같은 횟수였음(이제 최소값 찾으면 됩니다)
				top = mid - 1;
				break;
			case 1: // 횟수가 M보다 적었음(숫자가 너무 크단뜻)
				top = mid - 1;
				break;
			case 2: // 횟수가 M보다 컸음(숫자가 너무 작단뜻) 혹은 인출값이 하루치 사용양보다 적었음
				bot = mid + 1;
				break;
			}
		}
		System.out.println(bot);
	}

	private static int check(int mid) {
		int left = mid;
		int cnt = 1;
		for (int i = 0; i < N; i++) {
			if (left >= numArr[i]) {
				left -= numArr[i];
			} else {
				if (numArr[i] > mid) {
					return 2;
				}
				left = mid;
				i--;
				cnt++;
				continue;
			}
		}
		if (cnt == M) {
			return 0;
		} else if (cnt < M) {
			return 1;
		} else {
			return 2;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
