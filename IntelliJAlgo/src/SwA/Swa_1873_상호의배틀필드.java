import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swa_1873_상호의배틀필드 {
	/*
	 * 0 : 상 1 : 하 2 : 좌 3 : 우 (평지). -> 0번 (벽돌)* -> 1번 (강철)# -> 2번 (물)- -> 3번
	 */
	private static void move(int dir) {
		int nx, ny = 0;
		nx = x + dx[dir];
		ny = y + dy[dir];
		if (0 <= nx && nx < H && 0 <= ny && ny < W) {
			if (field[nx][ny] == 0) {
				field[nx][ny] = 9;
				field[x][y] = 0;
				x = nx;
				y = ny;
			}
		}
	}

	static int dir;
	static int[][] field;
	static String order;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int x, y;
	static int H, W;

private static void war(int max) {
		for (int i = 0; i < max; i++) {
			char o = order.charAt(i);

			switch (o) {
			case 'U':
				dir = 0;
				move(dir);
				break;
			case 'D':
				dir = 1;
				move(dir);
				break;
			case 'L':
				dir = 2;
				move(dir);
				break;
			case 'R':
				dir = 3;
				move(dir);
				break;
			case 'S':
				shoot(x, y, dir);
				break;

			}

		}
	}

	private static void shoot(int x, int y, int dir) {
		switch (dir) {
		case 0:
			for (int i = x - 1; i >= 0; i--) {
				if (field[i][y] == 1) {
					field[i][y] = 0;
					return;
				} else if (field[i][y] == 2) {
					return;
				}
			}
			break;
		case 1:
			for (int i = x + 1; i < H; i++) {
				if (field[i][y] == 1) {
					field[i][y] = 0;
					return;
				} else if (field[i][y] == 2) {
					return;
				}
			}
			break;
		case 2:
			for (int i = y - 1; i >= 0; i--) {
				if (field[x][i] == 1) {
					field[x][i] = 0;
					return;
				} else if (field[x][i] == 2) {
					return;
				}
			}
			break;
		case 3:
			for (int i = y + 1; i < W; i++) {
				if (field[x][i] == 1) {
					field[x][i] = 0;
					return;
				} else if (field[x][i] == 2) {
					return;
				}
			}
			break;
		}
	}

	private static int intmap(char c) {

		switch (c) {
		case '.':
			return 0;
		case '*':
			return 1;
		case '#':
			return 2;
		case '-':
			return 3;
		case '^':
			dir = 0;
			return 9;
		case 'v':
			dir = 1;
			return 9;
		case '<':
			dir = 2;
			return 9;
		case '>':
			dir = 3;
			return 9;
		}

		return -1;
	}

	private static char charmap(int i) {
		switch (i) {
		case 0:
			return '.';
		case 1:
			return '*';
		case 2:
			return '#';
		case 3:
			return '-';
		case 9:
			switch (dir) {
			case 0:
				return '^';
			case 1:
				return 'v';
			case 2:
				return '<';
			case 3:
				return '>';
			}
		}
		return 'n';
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = stoi(st.nextToken());
			W = stoi(st.nextToken());
			field = new int[H][W];
			for (int i = 0; i < H; i++) {
				String s = br.readLine();
				for (int j = 0; j < W; j++) {
					field[i][j] = intmap(s.charAt(j));
					if (field[i][j] == 9) {
						x = i;
						y = j;
					}
				}
			}
			int N = stoi(br.readLine());
			order = br.readLine();
			war(N);
			sb.append("#" + tc + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(charmap(field[i][j]));
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);

	}

}
