import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2458_키순서 {
	static int T, N, M;
	static List<Integer> graph[][]; // 0가 big, 1이 small
	static boolean visit[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		graph = new ArrayList[2][N + 1];
		visit = new boolean[N + 1];
		for (int i = 0; i < 2; i++) {
			for (int j = 1; j <= N; j++) {
				graph[i][j] = new ArrayList<Integer>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = stoi(st.nextToken());
			int b = stoi(st.nextToken());
			graph[0][s].add(b);
			graph[1][b].add(s);
		}
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 0; j < 2; j++) {
				Arrays.fill(visit, false);
				sum += check(i, j) - 1;
			}
			if (sum == N - 1) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}

	private static int check(int n, int dir) {
		visit[n] = true;
		int sum = 1;
		for (int i : graph[dir][n]) {
			if (!visit[i]) {
				sum += check(i, dir);
			}
		}
		return sum;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
