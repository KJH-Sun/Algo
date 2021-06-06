import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swa_평등주의 {
	static int N, K, answer;
	static int[] arr, copyArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			K = stoi(st.nextToken());

			arr = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = stoi(st.nextToken());
			}
			parametricSearch();
			// 정답 출력
			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void parametricSearch() {
		int left = 0;
		int right = 1000000000;
		while (left < right) {
			int mid = (left + right) / 2;
			if (!isPossible(mid)) {
				left = mid + 1;
				answer = left;
			} else {
				right = mid;
			}
		}
	}

	private static boolean isPossible(int mid) {
		copyArr = arr.clone();
		int oprCnt = 0;

		// 첫번째 원소부터 마지막 원소까지 인접한 두 수 차이(gap)에 따른 처리 (오른쪽 방향)
		for (int i = 1; i < N; i++) {
			int gap = copyArr[i + 1] - copyArr[i];
			if (gap >= mid) {
				oprCnt += (gap - mid);
				copyArr[i + 1] -= (gap - mid);
				if (oprCnt > K)
					return false;
			}
		}

		for (int i = N; i > 1; i--) {
			int gap = copyArr[i - 1] - copyArr[i];
			if (gap >= mid) {
				oprCnt += (gap - mid);
				copyArr[i - 1] -= (gap - mid);
				if (oprCnt > K)
					return false;
			}
		}
		return true;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}