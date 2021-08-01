import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swa_1868_파핑파핑지뢰찾기 {
	static int T, N;
	static char[][] map;
	static int[][] ck;
	static int[] dx = { 0, 0, 1, 1, 1, -1, -1, -1 };
	static int[] dy = { 1, -1, 0, 1, -1, 0, 1, -1 };
	static Queue<Node> que = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			map = new char[N][N];
			ck = new int[N][N];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '*') {
						ck[i][j] = -1;
					} else {
						checkBomb(i, j);
						if (ck[i][j] == 0) {
							que.offer(new Node(i, j));
						}
					}
				}
			}
			System.out.println("#" + tc + " " + bomb());
		}
	}

	private static void checkBomb(int x, int y) {
		int cnt = 0;
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (isValid(nx, ny) && map[nx][ny] == '*') {
				cnt++;
			}
		}
		ck[x][y] = cnt;
	}

	private static int bomb() {
		int cnt = 0;
		while (!que.isEmpty()) {
			Node n = que.poll();
			if (map[n.x][n.y] == '.') {
				cnt++;
				dfs(n.x, n.y);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '.') {
					cnt++;
				}
			}
		}

		return cnt;
	}

	private static void dfs(int x, int y) {
		map[x][y] = (char) (ck[x][y] + '0');
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (!isValid(nx, ny)) {
				continue;
			}
			if (ck[nx][ny] == 0 && map[nx][ny] == '.') {
				dfs(nx, ny);
			}
			map[nx][ny] = (char) (ck[nx][ny] + '0');
		}
	}

	private static boolean isValid(int x, int y) {
		return 0 <= x && 0 <= y && x < N && y < N;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
