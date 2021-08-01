package 최단거리;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.Function;

public class BOJ_11780_플로이드2 {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int N, M;
	static int[][] map;
	static int[][] prev;
	static final int INF = (int) 1e9;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi.apply(br.readLine());
		M = stoi.apply(br.readLine());
		map = new int[N + 1][N + 1];
		prev = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(map[i], INF);
			Arrays.fill(prev[i], -1);
		}
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = stoi.apply(st.nextToken());
			int e = stoi.apply(st.nextToken());
			int c = stoi.apply(st.nextToken());
			if (map[s][e] > c) {
				map[s][e] = c;
				prev[s][e] = s;
			}
		}
		floyd();
		print();
		System.out.println(sb);
	}

	private static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append(map[i][j] == INF ? "0 " : map[i][j] + " ");
			}
			sb.append("\n");
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == INF) {
					sb.append("0 ");
				} else {
					Deque<Integer> route = new ArrayDeque<>();
					int now = j;
					route.push(j);

					while (i != now) {
						now = prev[i][now];
						route.push(now);
					}
					sb.append(route.size() + " ");
					while (!route.isEmpty()) {
						sb.append(route.pollFirst() + " ");
					}
				}
				sb.append("\n");
			}
		}
	}

	private static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if (i == k) {
					continue;
				}
				for (int j = 1; j <= N; j++) {
					if (k == j || i == j) {
						continue;
					}
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
						prev[i][j] = prev[k][j];
					}
				}
			}
		}

	}

}