import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2615_오목 {
	static int N;
	static int[] dx = { -1, 0, 1, 1 };
	static int[] dy = { 1, 1, 1, 0 };
	static int[] rvdx = { 1, 0, -1, -1 };
	static int[] rvdy = { -1, -1, -1, 0 };

	static int[][] map = new int[20][20];
	static boolean[][] visit = new boolean[20][20];
	static Queue<Node> black = new PriorityQueue<>();
	static Queue<Node> white = new PriorityQueue<>();
	static int cnt;
	static int winTeam;
	static Node win;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 19; j++) {
				map[i][j] = stoi(st.nextToken());
				if (map[i][j] == 1) {
					black.add(new Node(i, j));
				}
				if (map[i][j] == 2) {
					white.add(new Node(i, j));
				}
			}
		}
		check();
		System.out.println(winTeam);
		if (winTeam != 0) {
			System.out.println((win.x + 1) + " " + (win.y + 1));
		}

	}

	private static void check() {
		while (!black.isEmpty()) {
			Node n = black.poll();
			for (int i = 0; i < 4; i++) {
				cnt = 0;
				if (search(n.x, n.y, i, 1, 1)) {
					win = n;
					winTeam = 1;
					return;
				}
			}
		}
		while (!white.isEmpty()) {
			Node n = white.poll();
			for (int i = 0; i < 4; i++) {
				cnt = 0;
				if (search(n.x, n.y, i, 1, 2)) {
					win = n;
					winTeam = 2;
					return;
				}
			}
		}
	}

	private static boolean search(int x, int y, int dir, int pos, int team) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		cnt = Math.max(cnt, pos);
		if (0 <= nx && nx < 19 && 0 <= ny && ny < 19 && map[nx][ny] == team) {
			search(nx, ny, dir, pos + 1, team);
			if (cnt == 5) {
				if (pos == 1) {
					int nnx = x + rvdx[dir];
					int nny = y + rvdy[dir];
					if (0 <= nnx && nnx < 19 && 0 <= nny && nny < 19 && map[nnx][nny] == team) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	static class Node implements Comparable<Node> {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			int diff = this.y - o.y;
			return diff != 0 ? diff : this.x - o.x;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
