import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Swa_3289_서로소집합 {
	static int[] map;
	static int T, N, M;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			M = stoi(st.nextToken());
			map = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				map[i] = i;
			}
			sb.append("#" + tc + " ");
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				if (stoi(st.nextToken()) == 0) {
					union(stoi(st.nextToken()), stoi(st.nextToken()));
				} else {
					if (find(stoi(st.nextToken())) == find(stoi(st.nextToken()))) {
						sb.append("1");
					} else {
						sb.append("0");
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static int find(int u) {
		return map[u] = (map[u] == u ? u : find(map[u]));
	}

	static int union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u == v)
			return u;
		map[u] = v;
		return v;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
