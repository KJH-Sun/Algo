import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1520_내리막길 {
	static int T, N, M, map[][], dp[][];
	static int[] dx = { 0, 0, 1, -1 }; // 사방탐색을 위한 int 배열
	static int[] dy = { 1, -1, 0, 0 }; // 사방탐색을 위한 int 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		M = stoi(st.nextToken());
		N = stoi(st.nextToken());
		map = new int[M][N];
		dp = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = stoi(st.nextToken());
			}
			Arrays.fill(dp[i], -1);
		}

		System.out.println(dfs(0, 0));
	}

	private static int dfs(int x, int y) {
		dp[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (isValid(nx, ny) && map[nx][ny] < map[x][y]) {
				if (nx == M - 1 && ny == N - 1) {
					dp[x][y] += 1;
				}
				if (dp[nx][ny] >= 0) {
					dp[x][y] += dp[nx][ny];
				} else {
					dp[x][y] += dfs(nx, ny);
				}
			}
		}
		return dp[x][y];
	}

	private static boolean isValid(int x, int y) {
		return 0 <= x && 0 <= y && x < M && y < N;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
