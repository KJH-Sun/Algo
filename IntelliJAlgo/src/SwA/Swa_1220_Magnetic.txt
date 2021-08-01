import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swa_1220_Magnetic {
	static int[][] field;
	static boolean[][] check;
	static int ts;
	static int cnt;

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	private static void dfs(int x, int y, int ori) {
		int nx = x + 1;
		if (nx < ts) {
			if (field[nx][y] == 2) {
				if (!check[nx][y]) {
					cnt++;
					check[nx][y] = true;
					check[ori][y] = true;
					return;
				} else {
					return;
				}
			}
			dfs(nx, y, ori);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		for (int tc = 1; tc <= 10; tc++) {
			ts = stoi(br.readLine()); // 테이블 사이즈
			field = new int[ts][ts];
			check = new boolean[ts][ts];
			for (int i = 0; i < ts; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < ts; j++) {
					field[i][j] = stoi(st.nextToken());
				}
			}
			cnt = 0;
			for (int i = 0; i < ts; i++) {
				for (int j = 0; j < ts; j++) {
					if (field[i][j] == 1) {
						dfs(i, j, i);
					}
				}
			}
			
			sb.append("#" + tc + " " + cnt);
			sb.append("\n");

		}
		System.out.println(sb);

	}

}
