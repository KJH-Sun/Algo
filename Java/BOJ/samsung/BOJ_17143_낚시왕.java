package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/*
 * 시뮬레이션 문제
 * 1. 낚시왕이 오른쪽으로 한 칸 이동한다.
 * 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
 * 3. 상어가 이동한다.
 */


public class BOJ_17143_낚시왕 {
	static int R, C, M;
	static Shark[][] map;
	static PriorityQueue<Shark> sharks = new PriorityQueue<>();
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, 1, -1 };
	static int[] reverse = { 0, 2, 1, 4, 3 };
	static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		M = stoi(st.nextToken());
		map = new Shark[R][C];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken()) - 1;
			int y = stoi(st.nextToken()) - 1;
			int s = stoi(st.nextToken());
			int d = stoi(st.nextToken());
			int z = stoi(st.nextToken());
			map[x][y] = new Shark(x, y, s, d, z);
		}
		solve();
		System.out.println(sum);

	}

	private static void solve() {
		int king = -1;
		while (king != C - 1) {
			king++;
			fishing(king);
			check();
			sMove();
		}
	}

	private static void check() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != null) {
					sharks.add(map[i][j]);
					map[i][j] = null;
				}
			}
		}

	}

	private static void sMove() { // wall판정 바꿔야함
		while (!sharks.isEmpty()) {
			Shark s = sharks.poll();
			for (int i = 0; i < s.s; i++) {
				s.x += dx[s.d];
				s.y += dy[s.d];
				if (!isValid(s.x, s.y)) {
					s.d = reverse[s.d];
					s.x += (dx[s.d] * 2);
					s.y += (dy[s.d] * 2);
				}
			}
			map[s.x][s.y] = s;
		}
	}

	private static boolean isValid(int x, int y) {
		return 0 <= x && 0 <= y && x < R && y < C;
	}

	private static void fishing(int king) {
		for (int i = 0; i < R; i++) {
			if (map[i][king] != null) {
				sum += map[i][king].z;
				map[i][king] = null;
				break;
			}
		}
	}

	static class Shark implements Comparable<Shark> {
		int x;
		int y;
		int s;
		int d;// 속력
		int z; // 크기

		public Shark(int x, int y, int s, int d, int z) {
			this.x = x;
			this.y = y;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(Shark o) {
			return this.z - o.z;
		}

	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}