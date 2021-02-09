import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노 {
	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int max = Integer.MIN_VALUE;
	static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sum = map[i][j];
				visit[i][j] = true;
				dfs(1, i, j);
				holy(i, j);
				visit[i][j] = false;
			}
		}
		System.out.println(max);
	}

	private static void dfs(int depth, int x, int y) {
		if (depth == 4) {
			max = Math.max(max, sum);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (0 <= nx && nx < N && 0 <= ny && ny < M && !visit[nx][ny]) {
				sum += map[nx][ny];
				visit[nx][ny] = true;
				dfs(depth + 1, nx, ny);
				sum -= map[nx][ny];
				visit[nx][ny] = false;
			}
		}

	}

	private static void holy(int x, int y) {
		int cross = 0;
		int bsum = map[x][y];

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (0 <= nx && nx < N && 0 <= ny && ny < M) {
				bsum += map[nx][ny];
				cross++;
			}
		}
		switch (cross) {
		case 1:
		case 2:
			return;
		case 3:
			max = Math.max(max, bsum);
			return;
		case 4:
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				bsum -= map[nx][ny];
				max = Math.max(max, bsum);
				bsum += map[nx][ny];
			}
			return;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
