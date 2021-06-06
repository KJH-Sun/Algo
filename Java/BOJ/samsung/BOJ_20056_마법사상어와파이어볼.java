package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;



/*
 * 격자의 행과 열은 1번부터 N번까지 번호가 매겨져 있고, 1번 행은 N번 행과 연결되어 있고, 1번 열은 N번 열과 연결되어 있다.
 * 이 조건이 조금 어렵게 읽히지만, 간단하게 생각하면 구의 형태로 연결되어있다고 생각할 수 있다.
 * 
 * 
 * 1. 파이어볼 이동
 * 2. 이차원리스트로 생성한 Fire 객체의 특정 좌표에 2개 이상의 파이어볼이 있다면, 규칙에 따라 합친 후 4개로 재분할한다.
 * 3. K초가 지난 후 파이어볼 질량의 합
 */

public class BOJ_20056_마법사상어와파이어볼 {
	static int N, M, K;
	static int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
	static List<Fire>[][] map;
	static List<Fire> fires = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		map = new List[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken()) - 1;
			int y = stoi(st.nextToken()) - 1;
			int m = stoi(st.nextToken());
			int s = stoi(st.nextToken());
			int d = stoi(st.nextToken());
			fires.add(new Fire(x, y, m, s, d));

		}
		for (int i = 0; i < K; i++) {
			move();
			split();
		}
		int res = 0;
		for (Fire f : fires) {
			res += f.m;
		}
		System.out.println(res);

	}

	private static void split() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() <= 1) {
					map[i][j].clear();
					continue;
				}
				int mSum = 0;
				int sSum = 0;
				boolean even = map[i][j].get(0).d % 2 == 0 ? true : false;
				boolean odd = map[i][j].get(0).d % 2 == 1 ? true : false;
				for (Fire f : map[i][j]) {
					mSum += f.m;
					sSum += f.s;
					even = even & f.d % 2 == 0 ? true : false;
					odd = odd & f.d % 2 == 1 ? true : false;
					fires.remove(f);
				}
				int nMass = mSum / 5;
				int size = map[i][j].size();
				map[i][j].clear();
				int nSpeed = sSum / size;

				if (nMass == 0) {
					continue;
				}

				if (even | odd) { // 0 2 4 6
					for (int k = 0; k < 8; k += 2) {
						fires.add(new Fire(i, j, nMass, nSpeed, k));
					}
				} else {
					for (int k = 1; k < 8; k += 2) {
						fires.add(new Fire(i, j, nMass, nSpeed, k));
					}
				}
			}
		}

	}

	private static void move() {
		for (Fire f : fires) {
			int nx = (f.x + N + dir[f.d][0] * (f.s % N)) % N;
			int ny = (f.y + N + dir[f.d][1] * (f.s % N)) % N;
			f.x = nx;
			f.y = ny;
			map[nx][ny].add(f);
		}
	}

	static class Fire {
		int x;
		int y;
		int m;
		int s;
		int d;

		public Fire(int x, int y, int m, int s, int d) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}

	}

	private static boolean isValid(int x, int y) {
		return 0 <= x && 0 <= y && x < N && y < N;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}