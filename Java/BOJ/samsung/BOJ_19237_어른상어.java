package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * 로직 :
 * 이차원 배열을 생성한 Water 객체 배열로 생성 (냄새를 남긴 상어의 번호, 냄새가 남아있는 시간이 저장된 객체)
 * 상어의 현재 x,y 좌표, 현재 보고 있는 방향, 보고있는 방향 별 이동우선순위, 현재 살아있는지 아닌지를 적을 객체를 생성
 * 상어들을 리스트로 관리할 sList 생성
 * 상어가 한마리가 남을 때까지 반복을 돌려서 시간을 확인하고, 1000초가 넘길때까지 상어의 개수가 한마리가 되지 않는다면 -1을 리턴하고 종료.
 * 
 */


public class BOJ_19237_어른상어 {
	static int N, M, K;
	static Water[][] sea;
	static Shark[] sList;
	static int[] dy = { 0, 0, 0, -1, 1 };
	static int[] dx = { 0, -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		sea = new Water[N][N];
		sList = new Shark[M + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				sea[i][j] = new Water(stoi(st.nextToken()), -1);

				if (sea[i][j].num != 0) {
					sList[sea[i][j].num] = new Shark(i, j);
					sea[i][j].time = K;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			sList[i].dir = stoi(st.nextToken());
		}
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 1; k <= 4; k++) {
					sList[i].pDir[j][k] = stoi(st.nextToken());
				}
			}
		}
		solve();
	}

	private static void solve() {
		int t = 0;
		while (true) {
			if (t >= 1001) {
				System.out.println(-1);
				break;
			}
			if (countShark() == 1) {
				System.out.println(t);
				break;
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sea[i][j].time--;
				}
			}
			sMove();
			t++;
		}
	}

	private static void sMove() {
		outer: for (int i = M; i > 0; i--) {
			int nowDir = sList[i].dir;
			for (int j = 1; j <= 4; j++) {
				int pDir = sList[i].pDir[nowDir][j];
				int nx = sList[i].x + dx[pDir];
				int ny = sList[i].y + dy[pDir];
				if (!isValid(nx, ny)) {
					continue;
				}
				if (sea[nx][ny].time < 0) {
					sList[i].x = nx;
					sList[i].y = ny;
					sList[i].dir = pDir;
					continue outer;
				}
			}
			nowDir = sList[i].dir;
			for (int j = 1; j <= 4; j++) {
				int pDir = sList[i].pDir[nowDir][j];
				int nx = sList[i].x + dx[pDir];
				int ny = sList[i].y + dy[pDir];
				if (!isValid(nx, ny)) {
					continue;
				}
				if (sea[nx][ny].num == i && sea[nx][ny].time >= 0) {
					sList[i].x = nx;
					sList[i].y = ny;
					sList[i].dir = pDir;
					continue outer;
				}
			}
		}
		for (int i = M; i > 0; i--) {
			if (!sList[i].isAlive) {
				continue;
			}
			sea[sList[i].x][sList[i].y].time = K;
			sea[sList[i].x][sList[i].y].num = i;
		}
	}

	private static boolean isValid(int x, int y) {
		return 0 <= x && 0 <= y && x < N && y < N;
	}

	private static int countShark() {
		int cnt = 0;
		for (int i = M; i > 0; i--) {
			if (!sList[i].isAlive) {
				continue;
			}
			if (sea[sList[i].x][sList[i].y].num != i) {
				sList[i].isAlive = false;
				continue;
			}
			cnt++;
		}
		return cnt;
	}

	static class Water {
		int num;
		int time;

		Water(int num, int time) {
			this.num = num;
			this.time = time;
		}
	}

	static class Shark {
		int x;
		int y;
		int dir;
		int[][] pDir;
		boolean isAlive;

		public Shark(int x, int y) {
			this.x = x;
			this.y = y;
			this.dir = 0;
			this.pDir = new int[5][5];
			this.isAlive = true;
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}