
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_2999_비밀이메일 {
	static char[][] map;
	static int R, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int size = s.length();
		if (size == 1) {
			System.out.println(s);
			System.exit(0);
		}
		for (int i = 1; i < size; i++) {
			if (size % i == 0 && i <= size / i) {
				R = i;
				C = size / i;
			}
		}

		if (R == 0 && C == 0) {
			R = 1;
			C = 1;
		}
		map = new char[R][C];
		int cnt = 0;
		for (int i = 0; i < C; i++) {
			for (int j = 0; j < R; j++) {
				map[j][i] = s.charAt(cnt++);
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
		}
	}

}
