import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4485_녹색옷을입은애가젤다지 {
	static int[][] map;
	static int[][] dp;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static final int INF = (int) 1e9;
	static int N;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		while (true) {
			cnt++;
			N = stoi(br.readLine());
			if (N == 0) {
				break;
			}
			map = new int[N][N];
			dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = stoi(st.nextToken());
					dp[i][j] = INF;
				}
			}
			dijk();
			sb.append("Problem " + cnt + ": " + dp[N - 1][N - 1] + "\n");
		}
		System.out.println(sb);

	}

	private static void dijk() {
		boolean[][] visit = new boolean[N][N];
		dp[0][0] = map[0][0];
		PriorityQueue<Node> que = new PriorityQueue<>();
		que.offer(new Node(0, 0, map[0][0]));
		while (!que.isEmpty()) {
			Node n = que.poll();
			visit[n.x][n.y] = true;
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (isValid(nx, ny)) {
					if (dp[nx][ny] > dp[n.x][n.y] + map[nx][ny]) {
						dp[nx][ny] = dp[n.x][n.y] + map[nx][ny];
						if (!visit[nx][ny]) {
							que.offer(new Node(nx, ny, dp[nx][ny]));
						}
					}
				}
			}
		}
	}

	private static boolean isValid(int x, int y) {
		if (0 <= x && 0 <= y && x < N && y < N) {
			return true;
		}
		return false;
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

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
