import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1987_알파벳 {
	static int R, C;
	static boolean[] visit;
	static char[][] map;
	static int maxdis = Integer.MIN_VALUE;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		visit = new boolean[26];
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		move(0, 0, 1);
		System.out.println(maxdis);

	}

	private static void move(int x, int y, int movecnt) {
		visit[map[x][y] - 'A'] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (0 <= nx && nx < R && 0 <= ny && ny < C && !visit[map[nx][ny] - 'A']) {
				move(nx, ny, movecnt + 1);
			}
		}
		visit[map[x][y] - 'A'] = false;
		maxdis = Math.max(maxdis, movecnt);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
