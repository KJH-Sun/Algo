import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16926_배열돌리기1 {
	static int N, M, R;
	static int[][] field;
	static boolean[][] visit;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		R = stoi(st.nextToken());
		field = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				field[i][j] = stoi(st.nextToken());
			}
		}
		int cnt = Math.min(N, M) / 2;
		for (int i = 0; i < R; i++) {
			rotate(cnt);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(field[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void rotate(int cnt) {
		for (int i = 0; i < cnt; i++) {
			int x = i;
			int y = i;
			int temp = field[x][y];
			int idx = 0;
			while (idx < 4) {
				int nx = x + dx[idx];
				int ny = y + dy[idx];
				if (nx < i || ny < i || nx > N - 1 - i || ny > M - 1 - i) {
					idx++;
				} else {
					field[x][y] = field[nx][ny];
					x = nx;
					y = ny;
				}
			}
			field[i + 1][i] = temp;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
