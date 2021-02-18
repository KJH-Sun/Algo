import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;
	int time;

	Node() {
	}

	Node(int x, int y, int time) {
		this.x = x;
		this.y = y;
		this.time = time;
	}
}

public class BOJ_5427_불 {
	static int T, W, H;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] field;
	static Queue<Node> que = new LinkedList<>();
	static Node start;
	static Node n;
	static StringBuffer sb = new StringBuffer();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			W = stoi(st.nextToken());
			H = stoi(st.nextToken());
			field = new int[H][W];
			for (int i = 0; i < H; i++) {
				String s = br.readLine();
				for (int j = 0; j < W; j++) {
					switch (s.charAt(j)) {
					case '#':
						field[i][j] = -2;
						break;
					case '*':
						field[i][j] = -1;
						que.offer(new Node(i, j, -1));
						break;
					case '.':
						field[i][j] = 0;
						break;
					case '@':
						field[i][j] = 1;
						start = new Node(i, j, 0);
						break;
					}
				}
			}
			if (start.x == H - 1 || start.y == W - 1 || start.x == 0 || start.y == 0) {
				sb.append(1 + "\n");
				continue;
			}
			que.offer(start);
			int ans = bfs();
			if (ans == -1) {
				sb.append("IMPOSSIBLE\n");
			} else {
				sb.append((ans + 1) + "\n");
			}
			que.clear();
		}
		System.out.println(sb);

	}

	private static int bfs() {
		while (!que.isEmpty()) {
			n = que.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (n.time == -1) {
					if (0 <= nx && nx < H && 0 <= ny && ny < W && field[nx][ny] >= 0) {
						field[nx][ny] = -1;
						que.offer(new Node(nx, ny, -1));
					}
				} else {
					if (0 <= nx && nx < H && 0 <= ny && ny < W && field[nx][ny] == 0) {
						if (nx == H - 1 || ny == W - 1 || nx == 0 || ny == 0) {
							return n.time + 1;
						}
						field[nx][ny] = n.time + 1;
						que.offer(new Node(nx, ny, n.time + 1));
					}
				}
			}
		}
		return -1;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
