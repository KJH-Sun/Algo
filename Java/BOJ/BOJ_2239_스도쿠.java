import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_2239_스도쿠 {
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		for (int i = 0; i < 9; i++) {
			String s = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		solve(0, 0);

	}

	private static void solve(int row, int col) {
		if (col == 9) {
			solve(row + 1, 0);
			return;
		}
		if (row == 9) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}

		if (map[row][col] == 0) {
			for (int i = 1; i <= 9; i++) {
				if (check(row, col, i)) {
					map[row][col] = i;
					solve(row, col + 1);
				}
			}
			map[row][col] = 0;
			return;
		}

		solve(row, col + 1);

	}

	private static boolean check(int x, int y, int val) {
		for (int i = 0; i < 9; i++) {
			if (map[x][i] == val) {
				return false;
			}
			if (map[i][y] == val) {
				return false;
			}

		}

		int nx = x / 3 * 3;
		int ny = y / 3 * 3;
		for (int i = nx; i < nx + 3; i++) {
			for (int j = ny; j < ny + 3; j++) {
				if (map[i][j] == val) {
					return false;
				}
			}
		}

		return true;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
