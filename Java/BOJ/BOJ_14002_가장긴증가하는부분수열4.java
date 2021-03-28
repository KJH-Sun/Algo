import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_14002_가장긴증가하는부분수열4 {
	static int N;
	static int[] arr, dp;
	static Set<Integer>[] nList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		dp = new int[N];
		nList = new LinkedHashSet[N];
		for (int i = 0; i < N; i++) {
			arr[i] = stoi(st.nextToken());
			nList[i] = new LinkedHashSet<Integer>();
		}
		if (N == 1) {
			System.out.println(1);
			System.out.println(arr[0]);
			System.exit(0);
		}
		int max = 0;
		int mIdx = -1;
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j] && dp[i] < dp[j] + 1) {
					nList[i].clear();
					nList[i].addAll(nList[j]);
					dp[i] = dp[j] + 1;
				}
				if (dp[i] > max) {
					max = dp[i];
					mIdx = i;
				}
			}
			nList[i].add(arr[i]);
		}
		System.out.println(max);
		for (int i : nList[mIdx]) {
			System.out.printf(i + " ");
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
