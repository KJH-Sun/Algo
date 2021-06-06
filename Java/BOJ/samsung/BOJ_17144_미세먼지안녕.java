package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지안녕 {
	static int R, C, T;
	static int ac = -1;
	static Queue<Dust> dusts = new ArrayDeque<>();
	static int[][] map;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = stoi(st.nextToken()); // 행
		C = stoi(st.nextToken()); // 열
		T = stoi(st.nextToken()); // 시간
		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (ac == -1 && map[i][j] == -1) {
					ac = i;
				}
			}
		}

		for (int time = 0; time < T; time++) {
			solve();
		}

		int res = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				res += map[i][j];
			}
		}
		System.out.println(res + 2);// 공기청정기 -2를 더해주기
	}

	private static void solve() { // 미세먼지가 있는 곳들을 큐에 추가하기
		dusts.clear();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1 || map[i][j] == 0)
					continue;
				dusts.add(new Dust(i, j, map[i][j]));
			}
		}
		spread();
	}
	private static int[][] deepCopy(int[][] src) {
		int[][] result = new int[src.length][src[0].length];
		for (int i = 0; i < src.length; i++) {
			System.arraycopy(src[i], 0, result[i], 0, src[0].length);
		}
		return result;
	}
	private static void spread() { // 미세먼지 확산
		while (!dusts.isEmpty()) {
			Dust n = dusts.poll();
			if (n.quan < 5) {
				continue;
			}
			int amountOfSpread = n.quan / 5;
			int cnt = 0;
			for (int d = 0; d < 4; d++) {
				int nx = n.x + dx[d];
				int ny = n.y + dy[d];
				if (nx < 0 || nx >= R || ny < 0 || ny >= C)
					continue;
				if (map[nx][ny] == -1)
					continue;
				map[nx][ny] += amountOfSpread;
				++cnt;
			}
			map[n.x][n.y] -= amountOfSpread * cnt;
		}
		acpush();
	}

	private static void acpush() {// 공기청정기가 미세먼지 밀어냄
		int up = ac;
		int down = ac + 1;

		// 아래 왼 위, 순서 중요함
		for (int i = up - 1; i > 0; i--) { // 공기청정기 영역에 들어올 애들을 바꿔서 없앰
			map[i][0] = map[i - 1][0];
		}
		for (int i = 0; i < C - 1; i++) {
			map[0][i] = map[0][i + 1];
		}
		for (int i = 0; i < up; i++) {
			map[i][C - 1] = map[i + 1][C - 1];
		}

		for (int i = down + 1; i < R - 1; i++) {
			map[i][0] = map[i + 1][0];
		}
		for (int i = 0; i < C - 1; i++) {
			map[R - 1][i] = map[R - 1][i + 1];
		}
		for (int i = R - 1; i > down; i--) {
			map[i][C - 1] = map[i - 1][C - 1];
		}

		// 오른쪽으로 가는거
		for (int i = C - 1; i > 1; i--) {
			map[up][i] = map[up][i - 1];
			map[down][i] = map[down][i - 1];
		}

		map[up][1] = 0;
		map[down][1] = 0;

	}

	static class Dust {
		int x, y;
		int quan; // 남은 미세먼지 양

		Dust(int x, int y, int quan) {
			this.x = x;
			this.y = y;
			this.quan = quan;
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
