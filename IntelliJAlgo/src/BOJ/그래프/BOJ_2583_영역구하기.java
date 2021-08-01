import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2583_영역구하기 {
	static int N, M, K, cnt;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static PriorityQueue<Integer> pQ = new PriorityQueue<>();

	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		map = new boolean[N][M];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			coloring(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()));
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!map[i][j]) {
					cnt = 0;
					dfs(i, j);
					pQ.add(cnt);
				}
			}
		}
		System.out.println(pQ.size());
		while (!pQ.isEmpty()) {
			System.out.print(pQ.poll() + " ");
		}
	}

	private static void dfs(int x, int y) {
		map[x][y] = true;
		cnt++;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (isValid(nx, ny) && !map[nx][ny]) {
				dfs(nx, ny);
			}
		}
	}

	private static boolean isValid(int x, int y) {
		return 0 <= x && 0 <= y && x < N && y < M;
	}

	private static void coloring(int x1, int y1, int x2, int y2) {
		for (int i = x1; i < x2; i++) {
			for (int j = y1; j < y2; j++) {
				map[j][i] = true;
			}
		}

	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
