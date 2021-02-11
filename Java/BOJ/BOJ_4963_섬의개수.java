import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;

	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_4963_섬의개수 {
	static int W, H;
	static int[] dx = { 0, 0, -1, 1, 1, -1, 1, -1 };
	static int[] dy = { 1, -1, 0, 0, 1, -1, -1, 1 };
	static int[][] field;
	static int cnt;
	static int max = Integer.MIN_VALUE;
	static Queue<Node> que = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			W = stoi(st.nextToken());
			H = stoi(st.nextToken());
			if (W == 0 && H == 0) {
				break;
			}
			field = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					field[i][j] = stoi(st.nextToken());
				}
			}
			cnt = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (field[i][j] == 1) {
						que.offer(new Node(i, j));
						bfs();
					}
				}
			}
			System.out.println(cnt);
		}
	}

	private static void bfs() {
		cnt++;
		while (!que.isEmpty()) {
			Node n = que.poll();
			for (int i = 0; i < 8; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (0 <= nx && nx < H && 0 <= ny && ny < W && field[nx][ny] == 1) {
					field[nx][ny] = field[n.x][n.y] + 1;
					que.offer(new Node(nx, ny));
				}
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
