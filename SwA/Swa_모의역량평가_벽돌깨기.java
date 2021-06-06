import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swa_모의역량평가_벽돌깨기 {
	static int T;
	static int N, W, H;
	static Queue<Node> que = new ArrayDeque<>();
	static int min;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			int[][] map = new int[H][W];
			min = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			solve(map, 0);
			System.out.printf("#%d %d%n", tc, min == Integer.MAX_VALUE ? 0 : min);
		}
	}

	static void solve(int[][] map, int cnt) {
		if (cnt == N) {
			int sum = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (map[i][j] != 0)
						sum++;
				}
			}
			min = Math.min(min, sum);
			return;
		}

		for (int i = 0; i < W; i++) {
			// 깰수 있는 벽돌이 하나도 없는 라인으로는 안던짐
			int j = existBlock(i, map);
			if (j == -1) {
				continue;
			}
			int[][] tmp = deepCopy(map);
			que.add(new Node(j, i));
			destroyBrick(tmp);
			solve(tmp, cnt + 1);
		}

	}

	private static int existBlock(int x, int[][] map) { // 깰 블록이 없으면 -1 리턴
		for (int i = 0; i < H; i++) {
			if (map[i][x] != 0) {
				return i;
			}
		}
		return -1;
	}

	static void destroyBrick(int[][] map) {
		while (!que.isEmpty()) {
			Node n = que.poll();
			int range = map[n.x][n.y] - 1;
			map[n.x][n.y] = 0;
			for (int j = 0; j < 4; j++) {
				int nx = n.x;
				int ny = n.y;
				for (int i = 0; i < range; i++) {
					nx += dx[j];
					ny += dy[j];
					if (!isValid(nx, ny)) {
						continue;
					}
					if (map[nx][ny] == 0) {
						continue;
					}
					if (map[nx][ny] == 1) {
						map[nx][ny] = 0;
					} else {
						que.offer(new Node(nx, ny));
					}
				}
			}
		}

		// 칸 내리기
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < W; i++) {
			for (int j = H - 1; j >= 0; j--) {
				if (map[j][i] != 0) {
					q.add(map[j][i]);
					map[j][i] = 0;
				}
			}
			for (int j = H - 1; j >= 0; j--) {
				if (!q.isEmpty()) {
					map[j][i] = q.poll();
				} else {
					break;
				}
			}
		}
	}

	private static boolean isValid(int nx, int ny) {
		return 0 <= nx && 0 <= ny && nx < H && ny < W;
	}

	private static int[][] deepCopy(int[][] src) {
		int[][] result = new int[src.length][src[0].length];
		for (int i = 0; i < src.length; i++) {
			System.arraycopy(src[i], 0, result[i], 0, src[0].length);
		}
		return result;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
