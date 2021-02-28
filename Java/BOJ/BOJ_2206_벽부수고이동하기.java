import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206_벽부수고이동하기 {
	static int N, M;
	static int[][] map;
	static int[][] visit;
	static List<Node> wall = new ArrayList<>();
	static int minDis = Integer.MAX_VALUE;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		map = new int[N][M];
		visit = new int[N][M];
		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = c[j] - '0';
				visit[i][j] = Integer.MAX_VALUE;
			}
		}
		map[0][0] = -1;
		bfs();
		if (minDis == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(minDis);
		}

	}

	private static void bfs() {
		Queue<Node> que = new LinkedList<>();
		que.offer(new Node(0, 0, 0, 1));

		while (!que.isEmpty()) {
			Node n = que.poll();
			if (n.x == N - 1 && n.y == M - 1) {
				minDis = Math.min(minDis, n.dis);
			}
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (0 > nx || nx >= N || 0 > ny || ny >= M) {
					continue;
				}
				if (visit[nx][ny] <= n.destroy) {
					continue;
				}
				if (map[nx][ny] == 0) {
					que.offer(new Node(nx, ny, n.destroy, n.dis + 1));
					visit[nx][ny] = n.destroy;
				}
				if (map[nx][ny] == 1) {
					if (n.destroy == 1) {
						continue;
					} else {
						que.offer(new Node(nx, ny, 1, n.dis + 1));
					}
				}
			}
		}
	}

	static class Node {
		int x;
		int y;
		int destroy;
		int dis;

		Node(int x, int y, int destroy, int dis) {
			this.x = x;
			this.y = y;
			this.destroy = destroy;
			this.dis = dis;
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
