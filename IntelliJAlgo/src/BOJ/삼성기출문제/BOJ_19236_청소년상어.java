package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;



/*
 * 1.물고기가 각자의 방향으로 이동한다
 * 1-1 이동할 때, 내 방향으로 이동할 수 없으면(상어가 있거나, 격자 밖이거나)
 * 1-2 반시계방향으로 45도씩 회전하며 이동가능한 방향을 찾는다.
 * 1-3 이동하려는 방향에 다른 물고기가 있다면, 자리를 바꾼다.
 * 2. 상어가 이동한다. 상어가 향하고 있는 방향의 물고기들 중 하나를 선택해서 먹을 수 있고, 먹은 물고기의 방향을 갖는다.
 * 2-1 상어가 먹을 물고기가 없으면, 종료한다.
 * 
 * 로직  : 시뮬레이션으로 구현하고, 선택 비선택으로 상어가 먹을 물고기를 모두 탐색하면서 먹은 물고기 번호의 합이 가장 큰 숫자를 찾는다.
 * 
 */


public class BOJ_19236_청소년상어 {
	static int[] fishDir = new int[17];
	static int[][] dir = { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 } };
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		List<Fish> fishes = new ArrayList<>();
		int[][] map = new int[4][4];
		
		// 입력 받기
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				Fish f = new Fish(i, j, stoi(st.nextToken()), stoi(st.nextToken()) - 1, true);
				fishes.add(f);
				map[i][j] = f.id;
			}
		}
		
		// 번호순으로 물고기를 정렬한다.(번호가 빠른 물고기부터 움직이기 때문)
		Collections.sort(fishes, new Comparator<Fish>() {
			@Override
			public int compare(Fish o1, Fish o2) {
				return o1.id - o2.id;
			}
		});

		
		// 격자의 0, 0 에 상어를 처음 세팅하기
		Fish f = fishes.get(map[0][0] - 1);
		Shark s = new Shark(0, 0, f.dir, f.id);
		f.isAlive = false;
		map[0][0] = -1;
		solve(map, s, fishes);
		System.out.println(max);
	}

	private static void solve(int[][] map, Shark s, List<Fish> fishes) {
		// 지금까지 먹은게 max보다 크면 max 갱신
		if (max < s.eatSum) {
			max = s.eatSum;
		}
		
		// fishes 배열 순회하면서 물고기들 움직이게하기
		fishes.forEach(e -> fMove(e, map, fishes));

		for (int dist = 1; dist < 4; dist++) {
			int nx = s.x + dir[s.dir][0] * dist;
			int ny = s.y + dir[s.dir][1] * dist;

			if (isValid(nx, ny) && map[nx][ny] > 0) {
				int[][] tmp = deepCopy(map);
				List<Fish> fCopy = copyList(fishes);

				tmp[s.x][s.y] = 0;
				Fish f = fCopy.get(map[nx][ny] - 1);
				Shark nS = new Shark(f.x, f.y, f.dir, s.eatSum + f.id);
				f.isAlive = false;
				tmp[f.x][f.y] = -1;

				solve(tmp, nS, fCopy);
			}
		}
	}

	// 물고기 움직이는 메서드
	private static void fMove(Fish f, int[][] map, List<Fish> fishes) {
		if (f.isAlive == false)
			return;

		for (int i = 0; i < 8; i++) {
			int nextDir = (f.dir + i) % 8;
			int nx = f.x + dir[nextDir][0];
			int ny = f.y + dir[nextDir][1];

			if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && map[nx][ny] > -1) {
				map[f.x][f.y] = 0;

				if (map[nx][ny] == 0) {
					f.x = nx;
					f.y = ny;
				} else {
					Fish temp = fishes.get(map[nx][ny] - 1);
					temp.x = f.x;
					temp.y = f.y;
					map[f.x][f.y] = temp.id;

					f.x = nx;
					f.y = ny;
				}

				map[nx][ny] = f.id;
				f.dir = nextDir;
				return;
			}
		}
	}

	private static List<Fish> copyList(List<Fish> fishes) {
		List<Fish> temp = new ArrayList<>();
		fishes.forEach(e -> temp.add(new Fish(e.x, e.y, e.id, e.dir, e.isAlive)));
		return temp;
	}

	private static int[][] deepCopy(int[][] src) {
		int[][] result = new int[src.length][src[0].length];
		for (int i = 0; i < src.length; i++) {
			System.arraycopy(src[i], 0, result[i], 0, src[0].length);
		}
		return result;
	}

	private static boolean isValid(int x, int y) {
		return 0 <= x && x < 4 && 0 <= y && y < 4;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static class Shark {
		int x, y, dir, eatSum;

		Shark(int x, int y, int dir, int eatSum) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.eatSum = eatSum;
		}
	}

	static class Fish {
		int x, y, id, dir;
		boolean isAlive = true;

		Fish(int x, int y, int id, int dir, boolean isAlive) {
			this.x = x;
			this.y = y;
			this.id = id;
			this.dir = dir;
			this.isAlive = isAlive;
		}
	}
}