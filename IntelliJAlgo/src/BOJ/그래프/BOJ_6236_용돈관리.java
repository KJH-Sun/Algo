import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * bot을 1로 잡고, max는 현우가 이용할 금액들 중 최대값 * i로 잡고, 그 중간지점으로 시작하여
 * 만약 인출횟수가 목표로하는 숫자보다 적다면 현재 인출하는 금액이 너무 크다는 뜻이므로 top을 mid-1로 옮겨서 다시 탐색
 * 크다면, 인출금액이 너무 작다는 뜻이므로 bot을 mid+1로 올림
 * 같다면, 이제 인출 최소금액을 찾으면 됩니다. 최대값을 찾는게 아니라 최소값을 찾는 것이므로, 
 * top을 mid-1로 낮추고 다시 check 메서드 실행
 * bot이 top보다 크거나 같아지면 bot을 출력
 * 
 */

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
