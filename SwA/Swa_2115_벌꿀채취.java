import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Swa_2115_벌꿀채취 {
	static int T, N, M, C, map[][], dp[][], maxV;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			M = stoi(st.nextToken());
			C = stoi(st.nextToken());
			map = new int[N][N];
			dp = new int[N][N - M + 1];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = stoi(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= N - M; j++) {
					maxProfit(i, j);
				}
			}
			maxV = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= N - M; j++) {
					maxCheck(i, j);
				}
			}
			System.out.println("#" + tc + " " + maxV);
		}
	}

	private static void maxCheck(int x, int y) {
		int max = dp[x][y];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				if (i == x && Math.abs(y - j) < M) {
					continue;
				}
				max += dp[i][j];
				maxV = Math.max(maxV, max);
				max -= dp[i][j];
			}
		}
	}

	private static int maxProfit(int xx, int yy) {
		int tmp = 0;
		int[][] arr = new int[M][2];
		for (int i = 0; i < M; i++) {
			tmp += map[xx][yy + i];
			arr[i][0] = map[xx][yy + i];
			arr[i][1] = map[xx][yy + i] * map[xx][yy + i];
		}
		if (tmp <= C) {
			int sum = 0;
			for (int i = 0; i < M; i++) {
				sum += arr[i][1];
			}
			return dp[xx][yy] = sum;
		} else {
			maxV = 0;
			solve(0, 0, 0, arr);
			return dp[xx][yy] = maxV;
		}

	}

	private static void solve(int pos, int cnt, int sum, int[][] arr) {
		if (cnt > C) {
			return;
		}
		if (pos == M) {
			maxV = Math.max(sum, maxV);
			return;
		}
		solve(pos + 1, cnt + arr[pos][0], sum + arr[pos][1], arr);
		solve(pos + 1, cnt, sum, arr);
		return;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
