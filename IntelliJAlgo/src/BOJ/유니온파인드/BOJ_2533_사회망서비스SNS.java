import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Function;


/*
 * 스티커 모으기(2)랑 어쩌면 비슷했던 개념의 문제.
 * 처음에는 트리를 구현해서 풀 수 있을거라고 생각했는데, 
 * 어떤 노드라도 루트가 될 수 있다는 점을 망각하여 결국 로직을 다시 구성해야했습니다.
 * dp로 접근하여, 루트노드를 1로 잡고 루트가 얼리어답터인 경우 vs 아닌 경우로 나누어 탑다운 dp로 풀이
 */


public class BOJ_2533_사회망서비스SNS {
	static int N;
	static Function<String, Integer> stoi = Integer::parseInt;
	static int[][] dp;
	static List<List<Integer>> sns = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi.apply(br.readLine());
		dp = new int[N + 1][2];
		StringTokenizer st;
		for (int i = 0; i <= N; i++) {
			sns.add(new ArrayList<>());
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = stoi.apply(st.nextToken());
			int b = stoi.apply(st.nextToken());
			sns.get(a).add(b);
			sns.get(b).add(a);
		}
		dp(1, -1); // dp[1][0]은 루트노드가 얼리어답터가 아닌 경우의 수, [1]은 루트노드가 얼리어답터인 경우
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}

	private static void dp(int cur, int parent) {
		dp[cur][0] = 0;
		dp[cur][1] = 1;
		for (int next : sns.get(cur)) {
			if (next != parent) {
				dp(next, cur);
				dp[cur][0] += dp[next][1];
				dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
			}
		}
	}

}