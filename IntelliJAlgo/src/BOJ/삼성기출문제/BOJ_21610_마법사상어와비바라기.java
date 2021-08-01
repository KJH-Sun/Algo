package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;

public class BOJ_21610_마법사상어와비바라기 {
	static int N, M, map[][];
	static Function<String, Integer> stoi = Integer::parseInt;
	static int[][] dir = new int[][] { { 0, 0 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 },
			{ 1, 0 }, { 1, -1 } };
	static List<Node> clouds = new ArrayList<>();
	static Queue<Direc> direcs = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi.apply(st.nextToken());
		M = stoi.apply(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = stoi.apply(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			direcs.add(new Direc(stoi.apply(st.nextToken()), stoi.apply(st.nextToken())));
		}
		// 초기 구름 위치 추가
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				clouds.add(new Node(N - 2 + i, j));
			}
		}
		while (!direcs.isEmpty()) {
			moveCloud();
			rainDrop();
			waterPasteBug();
			createCloud();
		}
		System.out.println(total());

	}

	private static int total() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cnt += map[i][j];
			}
		}
		return cnt;
	}

	private static void rainDrop() {
		for (Node n : clouds) {
			map[n.x][n.y]++;
		}
	}

	private static void createCloud() {
		List<Node> tmp = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= 2) {
					if (!clouds.contains(new Node(i, j))) {
						tmp.add(new Node(i, j));
						map[i][j] -= 2;
					}
				}
			}
		}
		clouds.clear();
		clouds.addAll(tmp);
	}

	private static void waterPasteBug() {
		for (Node n : clouds) {
			map[n.x][n.y] += checkDiagonal(n.x, n.y);
		}

	}

	private static int checkDiagonal(int x, int y) {
		int cnt = 0;
		for (int dx : new int[] { 1, -1 }) {
			for (int dy : new int[] { 1, -1 }) {
				int nx = x + dx;
				int ny = y + dy;
				if (isValid(nx, ny) && map[nx][ny] != 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static boolean isValid(int x, int y) {
		return 0 <= x && 0 <= y && x < N && y < N;
	}

	private static void moveCloud() {
		Direc d = direcs.poll();
		List<Node> tmp = new ArrayList<>();
		for (Node n : clouds) {
			tmp.add(new Node(mod(n.x + dir[d.d][0] * d.s), mod(n.y + dir[d.d][1] * d.s)));
		}
		clouds.clear();
		clouds.addAll(tmp);
	}

	private static int mod(int num) {
		while (num < 0) {
			num += N;
		}
		return num % N;
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			Node n = (Node) obj;
			return this.x == n.x && this.y == n.y;
		}
	}

	static class Direc {
		int d;
		int s;

		public Direc(int d, int s) {
			this.d = d;
			this.s = s;
		}

	}
}