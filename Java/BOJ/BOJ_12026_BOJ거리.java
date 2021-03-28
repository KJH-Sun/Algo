import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_12026_BOJ거리 {
	static int N;
	static int[] arr;
	static int[] dp; // 특정 블록까지 가기 위한 최소에너지

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		String s = br.readLine();
		dp = new int[s.length()];
		arr = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case 'B':
				arr[i] = 2;
				break;
			case 'O':
				arr[i] = 1;
				break;
			case 'J':
				arr[i] = 0;
				break;
			}
		}
		Arrays.fill(dp, -1);
		dp[0] = 0;
		for (int i = 1; i < s.length(); i++) {
			for (int j = 0; j < i; j++) {
				if ((arr[i] + 1) % 3 == arr[j] && dp[j] != -1) {
					if (dp[i] > dp[j] + (i - j) * (i - j) || dp[i] == -1) {
						dp[i] = dp[j] + (i - j) * (i - j);
					}
				}
			}
		}
		System.out.println(dp[N - 1]);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
