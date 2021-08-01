import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class 프렌즈4블록 {
	static boolean[][] check;
	static char[][] map;
	static Queue<Character> que = new ArrayDeque<>();

	public static void main(String[] args) {
//		int m = 4;
//		int n = 5;
		int m = 6;
		int n = 6;
//		String[] board = { "CCBDE", "AAADE", "AAABF", "CCBBF" };
		String[] board = { "TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ" };
		System.out.println(solution(m, n, board));
	}

	public static int solution(int m, int n, String[] board) {
		boolean update = false;
		check = new boolean[board.length][board[0].length()];
		map = new char[board.length][board[0].length()];
		int sum = 0;

		for (int i = 0; i < board.length; i++) {
			map[i] = board[i].toCharArray();
		}

		while (true) {
			update = false;
			for (int i = 0; i < check.length; i++) {
				Arrays.fill(check[i], false);
			}
			for (int i = 0; i < board.length - 1; i++) {
				for (int j = 0; j < board[0].length() - 1; j++) {
					if (map[i][j] != 'X' && four(i, j, map)) {
						update = true;
					}
				}
			}
			if (!update) {
				break;
			}

			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[0].length(); j++) {
					if (check[i][j]) {
						map[i][j] = 'X';
						sum++;
					}
				}
			}

			pull();

		}

		return sum;
	}

	private static void pull() {
		for (int i = 0; i < map[0].length; i++) {
			for (int j = map.length - 1; j >= 0; j--) {
				if (map[j][i] != 'X') {
					que.offer(map[j][i]);
					map[j][i] = 'X';
				}
			}
			int j = map.length;
			while (!que.isEmpty()) {
				map[--j][i] = que.poll();
			}
		}
	}

	private static boolean four(int x, int y, char[][] map) {
		char c = map[x][y];
		boolean flag = true;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (c != map[x + i][y + j]) {
					flag = false;
				}
			}
		}

		if (flag) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					check[x + i][y + j] = true;
				}
			}
		}
		return flag;
	}
}
