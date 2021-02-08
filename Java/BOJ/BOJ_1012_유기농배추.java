import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Vegi {
	int x;
	int y;

	Vegi() {
	}

	Vegi(int x, int y) {
		this.x = x;
		this.y = y;
	}

}

public class BOJ_1012_유기농배추 {
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int M, N, K;
	static HashMap<Integer, Vegi> field = new HashMap<>();
	static int cnt;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		int T = stoi(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			M = stoi(st.nextToken());
			K = stoi(st.nextToken());
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = stoi(st.nextToken());
				int y = stoi(st.nextToken());
				field.put(i, new Vegi(x, y));
			}
			visit = new boolean[K];
			cnt = K;
			for (int i = 0; i < K; i++) {
				if (!visit[i]) {
					visit[i] = true;
					dfs(field.get(i).x, field.get(i).y);
				}
			}
			field.clear();
			sb.append(cnt + "\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int x, int y) {

		for (int j = 0; j < 4; j++) {
			int nx = x + dx[j];
			int ny = y + dy[j];
			for (int i = 0; i < field.size(); i++) {
				if (nx == field.get(i).x && ny == field.get(i).y && !visit[i]) {
					cnt--;
					visit[i] = true;
					dfs(nx, ny);
				}
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
