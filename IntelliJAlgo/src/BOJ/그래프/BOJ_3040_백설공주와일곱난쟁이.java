import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_3040_백설공주와일곱난쟁이 {
	static int N = 9;
	static int[][] field;
	static StringBuffer sb = new StringBuffer();
	static int[] height;
	static boolean[] check;
	static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		height = new int[N];
		check = new boolean[N];
		for (int i = 0; i < N; i++) {
			height[i] = stoi(br.readLine());
		}
		sum = Arrays.stream(height).sum();
		comb(0, 0, sum);
	}

	private static void comb(int pos, int cnt, int total) {
		if (pos == N) {
			if (cnt == 2 && total == 100) {
				for (int i = 0; i < N; i++) {
					if (!check[i]) {
						System.out.println(height[i]);
					}
				}
				return;
			}
			return;
		}
		check[pos] = true;
		comb(pos + 1, cnt + 1, total - height[pos]);
		check[pos] = false;
		comb(pos + 1, cnt, total);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
