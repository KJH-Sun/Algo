import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_2116_주사위쌓기 {
	static int T;
	static int[] partn = new int[] { 5, 3, 4, 1, 2, 0 };
	static int[][] cube;
	static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = stoi(br.readLine());
		cube = new int[T][6];
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				cube[i][j] = stoi(st.nextToken());
			}
		}
		for (int i = 0; i < 6; i++) {
			solve(0, cube[0][i], 0);
		}
		System.out.println(sum);

	}

	private static void solve(int depth, int bot, int total) {
		if (depth == T) {
			sum = Math.max(total, sum);
			return;
		}

		int max = 0;
		int index = -1;
		for (int i = 0; i < 6; i++) {
			if (cube[depth][i] == bot) {
				index = i;
				break;
			}
		}
		int top = cube[depth][partn[index]];
		for (int j = 1; j <= 6; j++) {
			if (j == bot || j == top) {
				continue;
			}
			max = Math.max(max, j);
		}
		solve(depth + 1, top, total + max);

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
