import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2447_별찍기 {
	static int N;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = stoi(br.readLine());
		// N=1일때 처리
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = ' ';
			}
		}
		draw(0, 0, N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	private static void draw(int x, int y, int side) {
		if (side == 3) {
			for (int i = x; i < x + 3; i++) {
				for (int j = y; j < y + 3; j++) {
					if (i == x + 1 && j == y + 1) {
						map[i][j] = ' ';
						continue;
					}
					map[i][j] = '*';
				}
			}
			return;
		}
		int mid = side / 3;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1) {
					continue;
				}
				draw(x + i * mid, y + j * mid, mid);
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
