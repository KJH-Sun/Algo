package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * CCTV를 3차원배열로 선언하여 미리 만들어둡니다.
 * N과 M이 크지않고, CCTV가 8개뿐이므로 완탐으로 모든 경우의 수를 확인해봅니다.
 * 1. 입력받으면서 모든 CCTV의 위치를 리스트에 저장
 * 2. 리스트를 처음부터 순회하면서 회전할 수 있는 모든 방향을 확인
 * 2-1. 회전할 수 있는 방향의 수는 rotation 배열에 따로 저장(dir 배열의 2차원배열 size로 알수도있지만, 코드가 너무 길어져서 따로 뺐습니다.)
 * 2-2. 모든 회전하는 경우의 수를 전부 돌리고, 그 방향성의 감시 가능한 지역을 view메서드를 통해서 -1로 표시
 * 3. 선택비선택(조합)하는 메서드인 solve에서 리스트의 끝까지 모두 확인했다면, 맵 전체에서 사각지대의 개수를 세서 minBS 값과 비교.
 * 4. 최종 출력
 * 
 */


public class BOJ_15683_감시 {
	static int[][][] dir = { { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } }, // 1번 CCTV
			{ { 0, 1, 0, -1 }, { 1, 0, -1, 0 } }, // 2번 CCTV
			{ { -1, 0, 0, 1 }, { 0, 1, 1, 0 }, { 1, 0, 0, -1 }, { 0, -1, -1, 0 } }, // 3번 CCTV
			{ { 0, 1, -1, 0, 1, 0 }, { 0, 1, -1, 0, 0, -1 }, { 0, -1, -1, 0, 1, 0 }, { 0, -1, 0, 1, 1, 0 } }, // 4번 CCTV
			{ { 0, 1, -1, 0, 1, 0, 0, -1 } }, // 5번 CCTV
	};
	static int[] rotation = { 0, 4, 2, 4, 4, 1 };
	static int N, M, map[][];
	static List<Node> cctv = new ArrayList<>();

	static int minBS = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stoi(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] < 6) {
					cctv.add(new Node(i, j, map[i][j]));
				}
			}
		}
		solve(0);
		System.out.println(minBS);
	}

	private static void solve(int pos) {
		if (pos == cctv.size()) {
			countBlindSpot();
			return;
		}
		int[][] tmp = deepCopy(map);
		Node c = cctv.get(pos);
		for (int i = 0; i < rotation[c.cctv]; i++) {
			for (int j = 0; j < dir[c.cctv - 1][i].length; j += 2) {
				int nx = dir[c.cctv - 1][i][j];
				int ny = dir[c.cctv - 1][i][j + 1];
				view(c, nx, ny);
			}
			solve(pos + 1);
			map = deepCopy(tmp);
		}

	}

	private static void view(Node c, int dx, int dy) {
		int nx = c.x;
		int ny = c.y;
		while (true) {
			nx = nx + dx;
			ny = ny + dy;
			if (!isValid(nx, ny)) {
				break;
			}
			if (map[nx][ny] == 6) {
				break;
			}
			if (map[nx][ny] != 0) {
				continue;
			}
			map[nx][ny] = -1;
		}
	}

	private static void countBlindSpot() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					cnt++;
				}
			}
		}
		minBS = Math.min(minBS, cnt);
	}

	static class Node {
		int x;
		int y;
		int cctv;

		Node(int x, int y, int cctv) {
			this.x = x;
			this.y = y;
			this.cctv = cctv;
		}
	}

	private static int[][] deepCopy(int[][] src) {
		int[][] result = new int[src.length][src[0].length];
		for (int i = 0; i < src.length; i++) {
			System.arraycopy(src[i], 0, result[i], 0, src[0].length);
		}
		return result;
	}

	private static boolean isValid(int x, int y) {
		return 0 <= x && 0 <= y && x < N && y < M;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
