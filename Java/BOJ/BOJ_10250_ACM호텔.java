import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ_10250_ACM호텔 {
	static int T, H, W, N;
	static int[][] hotel;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			H = stoi(st.nextToken());
			W = stoi(st.nextToken());
			N = stoi(st.nextToken());
			hotel = new int[H][W];
			int cnt = 0;
			for (int i = 0; i < W; i++) {
				for (int j = 0; j < H; j++) {
					cnt++;
					hotel[j][i] = cnt;
					if (cnt == N) {
						if (i + 1 < 10) {
							System.out.printf("%d0%d\n", j + 1, i + 1);
						}else {
							System.out.printf("%d%d\n", j + 1, i + 1);
						}
					}

				}
			}
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
