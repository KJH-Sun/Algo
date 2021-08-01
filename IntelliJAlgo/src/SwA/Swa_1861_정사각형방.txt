import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swa_1861_정사각형방 {
	static int[][] field;
	static int N, move, maxRoom, maxMove;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Room> que = new LinkedList<>();

	static class Room {
		Room() {
		};

		Room(int x, int y) {
			this.x = x;
			this.y = y;
		}

		int x;
		int y;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	private static void bfs(int a, int b) {
		que.offer(new Room(a, b));
		Room r = new Room();
		int nx = 0, ny = 0;
		while (!que.isEmpty()) {
			move++;
			r = que.poll();
			int x = r.x;
			int y = r.y;
			for (int k = 0; k < 4; k++) {
				nx = x + dx[k];
				ny = y + dy[k];
				if (0 <= nx && nx < N && 0 <= ny && ny < N) {
					if (field[nx][ny] == field[x][y] + 1) {
						que.offer(new Room(nx, ny));
					}
				}
			}
		}
		if (maxMove < move) {
			maxRoom = field[a][b];
			maxMove = move;
		}
		if (maxMove == move && maxRoom > field[a][b]) {
			maxRoom = field[a][b];
			return;
		}

	}

	private static void dfs(int x, int y) {
		int nx = 0;
		int ny = 0;
		for (int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if (0 <= nx && nx < N && 0 <= ny && ny < N) {
				if (field[nx][ny] == field[x][y] + 1) {
					move++;
					dfs(nx, ny);
				}
			}
		}
		if (maxMove < move) {
			maxRoom = field[x][y];
			maxMove = move;
		}
		if (maxMove == move && maxRoom > field[x][y]) {
			maxRoom = field[x][y];
			return;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		int T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = stoi(br.readLine());
			field = new int[N][N];
			maxMove = Integer.MIN_VALUE;
			maxRoom = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					field[i][j] = stoi(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					move = 1; // bfs 돌릴땐 0
					dfs(i, j);
				}
			}

			sb.append("#" + tc + " " + maxRoom + " " + maxMove);

			sb.append("\n");
		}
		System.out.println(sb);

	}

}
