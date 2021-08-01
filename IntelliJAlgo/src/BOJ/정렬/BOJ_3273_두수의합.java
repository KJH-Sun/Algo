import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * numArr를 오름차순 정렬한 이후, start를 numArr의 가장 앞, end를 numArr의 끝에 둔다. 
 * 이후 두 수의 합이 크다면  end를 당기고, 작다면 start를 민다.
 * x와 합이 같다면, res++하고 end를 당긴다.
 */

public class BOJ_3273_두수의합 {
	static int N, M, X;
	static int[] numArr;
	static boolean[] visit;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		numArr = new int[N];
		visit = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numArr[i] = stoi(st.nextToken());
		}
		X = stoi(br.readLine());
		Arrays.sort(numArr);
		solve();
		System.out.println(res);
	}

	private static void solve() {
		int start = 0;
		int end = N - 1;
		int sum = 0;
		while (start < end) {
			sum = numArr[start] + numArr[end];
			if (sum > X) {
				end--;
			}
			if (sum < X) {
				start++;
			}
			if (sum == X) {
				res++;
				start++;
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
