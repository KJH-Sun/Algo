import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swa_1249_보급로 {
	static int N;
	static int[] dx = new int[] { 0, 1, 0, -1 };
	static int[] dy = new int[] { -1, 0, 1, 0 };
	static int[][] map;
	static int[][] dist;
	static final int INF = (int) 1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		int T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = stoi(br.readLine());
			map = new int[N][N];
			dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}
			sb.append("#" + tc + " " + bfs() + "\n");
		}
		System.out.println(sb);
	}

	private static int bfs() {
		PriorityQueue<Node> que = new PriorityQueue<>();
		que.offer(new Node(0, 0, 0));
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], INF);
		}
		dist[0][0] = 0;
		while (!que.isEmpty()) {
			Node now = que.poll();
			if (now.x == N - 1 && now.y == N - 1) {
				return now.w;
			}
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (isValid(nx, ny) && dist[nx][ny] > now.w + map[nx][ny]) {
					dist[nx][ny] = now.w + map[nx][ny];
					que.offer(new Node(nx, ny, now.w + map[nx][ny]));
				}
			}
		}
		return -1;
	}

	private static boolean isValid(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}

	static class Node implements Comparable<Node> {
		int x;
		int y;
		int w;

		Node(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
