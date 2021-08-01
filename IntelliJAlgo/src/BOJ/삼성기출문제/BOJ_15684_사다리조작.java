package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.Function;

public class BOJ_15684_사다리조작 {
	static int N, M, H, map[][];
	static Function<String, Integer> stoi = Integer::parseInt;
	static boolean flag = false;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi.apply(st.nextToken());
		M = stoi.apply(st.nextToken());
		H = stoi.apply(st.nextToken());

		map = new int[H + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi.apply(st.nextToken());
			int y = stoi.apply(st.nextToken());
			map[x][y] = 1; // 왼
			map[x][y + 1] = 2; // 오
		}
		for (int i = 0; i <= 3; i++) {
			result = i;
			buildLadder(1, 0);
			if (flag) {
				break;
			}
		}
		System.out.println(flag ? result : -1);
	}

	private static void buildLadder(int x, int cnt) {
		if (flag) {
			return;
		}
		if (cnt == result) { // 기저조건
			if (check()) {
				flag = true;
			}
			return;
		}

		for (int i = x; i <= H; i++) {
			for (int j = 1; j < N; j++) {
				if (map[i][j] == 0 && map[i][j + 1] == 0) {
					map[i][j] = 1;
					map[i][j + 1] = 2;
					buildLadder(i, cnt + 1);
					map[i][j] = 0;
					map[i][j + 1] = 0;
				}
			}
		}
	}

	private static boolean check() {
		for (int i = 1; i <= N; i++) {
			int x = 1;
			int y = i;
			for (int j = 0; j < H; j++) {
				switch (map[x][y]) {
				case 1:
					y++;
					break;
				case 2:
					y--;
					break;
				}
				x++;
			}
			if (y != i) {
				return false;
			}
		}
		return true;
	}

}