import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1992_쿼드트리 {
	static int N;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = Character.getNumericValue(s.charAt(j));
			}
		}
		System.out.println(solve(0, 0, N));

	}

	private static String solve(int x, int y, int n) {
		if (n == 2) {
			int[] numArr = new int[4];
			int cnt = 0;
			for (int i = x; i < x + 2; i++) {
				for (int j = y; j < y + 2; j++) {
					numArr[cnt++] = map[i][j];
				}
			}
			if (Arrays.stream(numArr).sum() == 0 || Arrays.stream(numArr).sum() == 4) {
				return Integer.toString(numArr[0]);
			} else {
				return "(" + Integer.toString(numArr[0]) + Integer.toString(numArr[1]) + Integer.toString(numArr[2])
						+ Integer.toString(numArr[3]) + ")";
			}
		}

		int side = n / 2;
		int cnt = 0;
		String[] sArr = new String[4];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				sArr[cnt++] = solve(x + i * side, y + j * side, side);
			}
		}
		if (sArr[0].equals(sArr[1]) && sArr[1].equals(sArr[2]) && sArr[2].equals(sArr[3])) {
			if (sArr[0].charAt(0) == '(') {
				return "(" + sArr[0] + sArr[1] + sArr[2] + sArr[3] + ")";
			}
			return sArr[0];
		} else {
			return "(" + sArr[0] + sArr[1] + sArr[2] + sArr[3] + ")";
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
