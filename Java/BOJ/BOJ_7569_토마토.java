import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569_토마토 {
	static int N, M, H;
	static int[][][] map;
	static int max = Integer.MIN_VALUE;
	static int[] dx = { 1, 0, -1, 0, 0, 0 }; // 동서남북위아래
	static int[] dy = { 0, 1, 0, -1, 0, 0 };
	static int[] dh = { 0, 0, 0, 0, 1, -1 };
	static Queue<Node> que = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		H = stoi(st.nextToken());
		map = new int[H][M][N];
		for (int l = 0; l < H; l++) {
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[l][i][j] = stoi(st.nextToken());
					// 0이 없을때 처리
				}
			}
		}
		for (int l = 0; l < H; l++) {
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (map[l][i][j] == 1) {
						que.offer(new Node(l, i, j));
					}
				}
			}
		}
		bfs();
		for (int l = 0; l < H; l++) {
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (map[l][i][j] == 0) {
						System.out.println(-1);
						System.exit(0);
					}
					max = Math.max(max, map[l][i][j]);
				}
			}
		}
		System.out.println(max - 1);
	}

	private static void bfs() {
		while (!que.isEmpty()) {
			Node n = que.poll();
			for (int i = 0; i < 6; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				int nh = n.h + dh[i];
				if (0 <= nh && nh < H && 0 <= nx && nx < M && 0 <= ny && ny < N && map[nh][nx][ny] == 0) {
					map[nh][nx][ny] = map[n.h][n.x][n.y] + 1;
					que.offer(new Node(nh, nx, ny));
				}
			}
		}

	}

	static class Node {
		int x;
		int y;
		int h;

		Node(int h, int x, int y) {
			this.x = x;
			this.y = y;
			this.h = h;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
