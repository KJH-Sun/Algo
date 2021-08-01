import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Swa_1974_스도쿠검증 {
	static LinkedList<Integer> list = new LinkedList<>();
	static int T;
	static int[][] field = new int[9][9];
	static boolean[] visit;
	static int res;

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			for (int i = 0; i < 9; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 9; j++) {
					field[i][j] = stoi(st.nextToken());
				}
			}
			loop: for (int i = 0; i < 9; i++) {
				visit = new boolean[9];
				for (int j = 0; j < 9; j++) {
					visit[field[i][j] - 1] = true;
				}
				for (boolean b : visit) {
					res = (b ? 1 : 0);
					if (res == 0) {
						break loop;
					}
				}
				visit = new boolean[9];
				for (int j = 0; j < 9; j++) {
					visit[field[j][i] - 1] = true;
				}
				for (boolean b : visit) {
					res = (b ? 1 : 0);
					if (res == 0) {
						break loop;
					}
				}
			}
			if (res == 1) {
				loop: for (int l = 0; l < 3; l++) {
					for (int k = 0; k < 3; k++) {
						visit = new boolean[9];
						for (int i = 0; i < 3; i++) {
							for (int j = 0; j < 3; j++) {
								visit[field[i + 3 * l][j + 3 * k] - 1] = true;
							}
						}
						for (boolean b : visit) {
							res = (b ? 1 : 0);
							if (res == 0) {
								break loop;
							}
						}
					}
				}
			}

			System.out.printf("#%d %d\n", tc, res);
		}
	}

}
