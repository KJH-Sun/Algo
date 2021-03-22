import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1717_집합의표현 {
	static int N, M;
	static int[] map;
	static int[] rank;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		map = new int[N + 1];
		rank = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			map[i] = i;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cal = stoi(st.nextToken());
			int u = stoi(st.nextToken());
			int v = stoi(st.nextToken());
			if (cal == 0) {
				union(u, v);
			} else {
				if (find(u) == find(v)) {
					sb.append("YES\n");
				} else {
					sb.append("NO\n");
				}
			}
		}
		System.out.println(sb);
	}

	private static int find(int u) {
		return map[u] = map[u] == u ? u : find(map[u]);
	}

	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u == v) {
			return;
		}
		if (rank[u] > rank[v]) {
			map[v] = map[u];
		} else {
			if (rank[u] == rank[v]) {
				rank[v]++;
			}
			map[u] = map[v];
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
