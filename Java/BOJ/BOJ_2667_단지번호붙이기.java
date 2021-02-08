import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2667_단지번호붙이기 {
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] field;
	static int[] result;
	static boolean[][] visit;
	static int N;
	static int cnt, total;

	static void dfs(int x, int y) {
		cnt++;
		visit[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (0 <= nx && nx < N && 0 <= ny && ny < N && !visit[nx][ny] && field[nx][ny] == 1) {
				dfs(nx, ny);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		N = stoi(br.readLine());
		field = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				field[i][j] = Character.getNumericValue(s.charAt(j));
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (field[i][j] == 1 && !visit[i][j]) {
					total++;
					dfs(i, j);
					sb.append(cnt + "\n");
					cnt = 0;
				}
			}
		}
		int[] res = new int[total];
		StringTokenizer st = new StringTokenizer(sb.toString(), "\n");
		for (int i = 0; i < total; i++) {
			res[i] = stoi(st.nextToken());
		}
		Arrays.sort(res);
		sb.setLength(0);
		sb.append(total + "\n");
		for (int i : res) {
			sb.append(i + "\n");
		}
		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
