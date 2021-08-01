import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636_치즈 {
	static int H, W;
	static int[][] map;
	static Queue<Node> que = new LinkedList<>();
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int tCh = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = stoi(st.nextToken());
		W = stoi(st.nextToken());
		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = stoi(st.nextToken());
				if (map[i][j] == 1) {
					tCh++;
				}
			}
		}
		int time = 0;
		int odd = 0;
		while (tCh > 0) {
			odd = tCh;
			time++;
			bfs();
		}
		System.out.println(time);
		System.out.println(odd);

	}

	private static void bfs() {
		que.offer(new Node(0, 0));
		boolean[][] visit = new boolean[H][W];
		visit[0][0] = true;
		while (!que.isEmpty()) {
			Node n = que.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (0 > nx || 0 > ny || nx >= H || ny >= W || visit[nx][ny]) {
					continue;
				}
				if (map[nx][ny] == 1) {
					visit[nx][ny] = true;
					map[nx][ny] = 0;
					tCh--;
				} else {
					que.offer(new Node(nx, ny));
				}
				visit[nx][ny] = true;
			}
		}
	}

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
