import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Swa_2001_파리퇴치 {
	static StringBuffer sb = new StringBuffer();
	static int N, M;
	static int[][] field;
	static int[] result;

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	private static void combat() {
		int limit = N - (M - 1);
		int count = 0;
		result = new int[limit * limit];
		for (int i = 0; i < limit; i++) {
			for (int j = 0; j < limit; j++) {
				for (int x = 0; x < M; x++) {
					for (int y = 0; y < M; y++) {
						result[count] += field[i + x][j + y];
					}
				}
				count++;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = stoi(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			M = stoi(st.nextToken());
			field = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					field[i][j] = stoi(st.nextToken());
				}
			}
			combat();
			sb.append("#" + tc + " " + Arrays.stream(result).max().getAsInt() + "\n");
		}
		System.out.println(sb);
	}
}
