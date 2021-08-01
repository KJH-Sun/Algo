import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class RCS {
	int r;
	int s;
	int c;

	RCS() {
	}

	RCS(int r, int c, int s) {
		this.r = r;
		this.s = s;
		this.c = c;
	}
}

public class BOJ_17406_배열돌리기4 {
	static int N, M, K;
	static int[][] field;
	static RCS[] rcs;
	static boolean[][] visit;
	static boolean[] permck;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		field = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				field[i][j] = stoi(st.nextToken());
			}
		}
		rcs = new RCS[K];
		permck = new boolean[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = stoi(st.nextToken());
			int c = stoi(st.nextToken());
			int s = stoi(st.nextToken());
			rcs[i] = new RCS(r, c, s);
		}

		perm(0);
		System.out.println(min);

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				sb.append(field[i][j] + " ");
//			}
//			sb.append("\n");
//		}
//		System.out.println(sb);
	}

	private static void perm(int depth) {
		if (depth == K) {
			for (int a[] : field) {
				min = Math.min(min, Arrays.stream(a).sum());
			}
			return;
		}

		int[][] temp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = field[i][j];
			}
		}
		for (int i = 0; i < K; i++) {
			if (!permck[i]) {
				permck[i] = true;
				rotate(rcs[i].r, rcs[i].c, rcs[i].s);
				perm(depth + 1);
				permck[i] = false;
				for (int o = 0; o < N; o++) {
					for (int p = 0; p < M; p++) {
						field[o][p] = temp[o][p];
					}
				}
			}
		}

	}

	private static void rotate(int r, int c, int s) {
		for (int i = 0; i < s; i++) {
			int x = r - s - 1 + i;
			int y = c - s - 1 + i;
			int temp = field[x][y];
			int idx = 0;
			while (idx < 4) {
				int nx = x + dx[idx];
				int ny = y + dy[idx];
				if (nx < r - s - 1 + i || ny < c - s - 1 + i || nx > r + s - 1 - i || ny > c + s - 1 - i) {
					idx++;
				} else {
					field[x][y] = field[nx][ny];
					x = nx;
					y = ny;
				}
			}
			field[r - s + i - 1][c - s + i] = temp;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
