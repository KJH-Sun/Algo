import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3109_빵집 {
	static int R, C, cnt;
	static char[][] map;
	static boolean success;
	static int[] dx = { -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < R; i++) {
			success = false;
			build(i, 0);
		}
		System.out.println(cnt);
	}

	private static void build(int rowNum, int colNum) {
		if (colNum == C - 1) {
			cnt++;
			success = true;
			return;
		}

		for (int i = 0; i < 3; i++) {
			int nx = rowNum + dx[i];
			int ny = colNum + 1;
			if (0 <= nx && nx < R && ny < C && map[nx][ny] == '.') {
				map[nx][ny] = 'x';
				build(nx, ny);
				if (success) {
					return;
				}
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
