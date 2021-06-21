package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 로직 세우는 것이 굉장히 까다로웠던 문제.
 * 그림을 그리며 침착하게 규칙을 찾으면, 결국 중요한 것은 방향과 순서라는 사실을 알 수 있다.
 */

public class BOJ_15685번_드래곤커브 {
	static int N;
	static boolean[][] map = new boolean[101][101];
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			draw(stoi(st.nextToken()), stoi(st.nextToken()), getDirections(stoi(st.nextToken()), stoi(st.nextToken())));
		}
		System.out.println(count());
	}

	private static int count() {
		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	public static List<Integer> getDirections(int d, int g) {
		List<Integer> directions = new ArrayList<>();
		directions.add(d);

		while (0 < g--) {
			for (int i = directions.size() - 1; 0 <= i; i--) {
				int direction = (directions.get(i) + 1) % 4;
				directions.add(direction);
			}
		}
		return directions;
	}

	public static void draw(int x, int y, List<Integer> directions) {
		map[x][y] = true;
		for (int direc : directions) {
			x += dx[direc];
			y += dy[direc];
			map[x][y] = true;
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}