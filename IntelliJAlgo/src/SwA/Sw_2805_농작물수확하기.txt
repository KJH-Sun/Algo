import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Swa_2805_농작물수확하기 {
	static int[][] field;

	static int ctoi(char c) {
		return Character.getNumericValue(c);
	}

	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = stoi(br.readLine());
			field = new int[N][N];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					field[i][j] = ctoi(s.charAt(j));
				}
			}
			int middle = N / 2 ;
			int count = 0;
			int plus = 1;
			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = middle - count; j <= middle + count; j++) {
					sum += field[i][j];
				}
				if (i == middle) {
					plus *= -1;
				}
				count += plus;
			}
			System.out.printf("#%d %d\n", tc ,sum);
		}

	}
}
