import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swa_1263_사람네트워크2 {
	static int T;
	static int[][] map;
	static final int INF = (int) 1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = stoi(st.nextToken());
			map = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] = stoi(st.nextToken());
					if (i != j && map[i][j] == 0) {
						map[i][j] = INF;
					}
				}
			}
			floydWarshall(N);
			int res = Integer.MAX_VALUE;
			for (int i = 1; i <= N; i++) {
				res = Math.min(res, Arrays.stream(map[i]).sum());
			}
			sb.append("#" + tc + " " + res + "\n");
		}
		System.out.println(sb);
	}

	private static void floydWarshall(int n) { // 가장 바깥 포문이 중간경유지
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
