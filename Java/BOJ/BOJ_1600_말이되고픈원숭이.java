import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600_말이되고픈원숭이 {
	static int W, H, K;
	static int[][] map;
	static boolean[][][] visit;
	static int[] dx = { 0, 0, 1, -1, -2, -2, -1, -1, 1, 1, 2, 2 };
	static int[] dy = { 1, -1, 0, 0, -1, 1, -2, 2, -2, 2, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = stoi(st.nextToken());
		H = stoi(st.nextToken());
		if (W == 1 && H == 1) {
			System.out.println(0);
			System.exit(0);
		}
		map = new int[H][W];
		visit = new boolean[H][W][K + 1];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Monkey> que = new LinkedList<>();
		que.offer(new Monkey(0, 0, 0, 0));
		for (int i = 0; i <= K; i++) {
			visit[0][0][i] = true;
		}
		while (!que.isEmpty()) {
			Monkey m = que.poll();
			for (int i = 0; i < dx.length; i++) {
				if (i > 3 && m.horse == K) {
					break;
				}
				int nx = m.x + dx[i];
				int ny = m.y + dy[i];
				if (0 <= nx && 0 <= ny && nx < H && ny < W && map[nx][ny] == 0) {
					if (nx == H - 1 && ny == W - 1) {
						return m.turn + 1;
					}
					if (i > 3) {
						if (!visit[nx][ny][m.horse + 1]) {
							visit[nx][ny][m.horse + 1] = true;
							que.offer(new Monkey(nx, ny, m.horse + 1, m.turn + 1));
						}
					} else {
						if (!visit[nx][ny][m.horse]) {
							visit[nx][ny][m.horse] = true;
							que.offer(new Monkey(nx, ny, m.horse, m.turn + 1));
						}
					}
				}
			}
		}
		return -1;
	}

	static class Monkey {
		int x;
		int y;
		int horse;
		int turn;

		Monkey(int x, int y, int horse, int turn) {
			this.x = x;
			this.y = y;
			this.horse = horse;
			this.turn = turn;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
