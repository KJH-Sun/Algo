package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 인구개방이 이루어지는지 확인한다.
 * 2. 인구개방이 이루어진다면, 인구를 조정한 이후 visit[]배열에 체크한다.
 * 3. 모든 좌표를 확인한다면, 1부터 반복한다.
 * 4. update가 없을때가지 반복한다.
 */


public class BOJ_16234_인구이동 {
	static int N, L, R, map[][];
	static boolean[][] visit;
	static int unionCnt;
	static int unionSum;
	static int[] dx = new int[] { 0, 0, -1, 1 };
	static int[] dy = new int[] { -1, 1, 0, 0 };
	static Queue<Node> que = new ArrayDeque<>();
	static Queue<Node> kingdom = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		L = stoi(st.nextToken());
		R = stoi(st.nextToken());
		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		System.out.println(day());

	}

	private static int day() {
		int cnt = 0;
		boolean update = false;

		while (true) {

			update = false;
			for (int i = 0; i < N; i++) {
				Arrays.fill(visit[i], false);
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j]) {
						if (merger(i, j)) {
							update = true;
						}
					}
				}
			}
			if (update) {
				cnt++;
			} else {
				return cnt;
			}
		}

	}

	private static boolean merger(int x, int y) {
		que.clear();
		kingdom.clear();

		que.offer(new Node(x, y));
		kingdom.offer(new Node(x, y));
		visit[x][y] = true;
		int sum = map[x][y];

		while (!que.isEmpty()) {

			Node n = que.poll();

			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];

				if (isValid(nx, ny) && !visit[nx][ny]) {
					int diff = Math.abs(map[nx][ny] - map[n.x][n.y]);
					if (diff >= L && diff <= R) {
						sum += map[nx][ny];
						que.offer(new Node(nx, ny));
						kingdom.offer(new Node(nx, ny));
						visit[nx][ny] = true;
					}
				}

			}
		}

		if (kingdom.size() == 1) {
			return false;
		} else {
			int ps = sum / kingdom.size();
			for (Node n : kingdom) {
				map[n.x][n.y] = ps;
			}
			return true;
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

	private static boolean isValid(int x, int y) {
		return 0 <= x && 0 <= y && x < N && y < N;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
