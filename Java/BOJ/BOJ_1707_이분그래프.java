import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1707_이분그래프 {
	static int T, V, E;
	static boolean[] visit;
	static boolean[] team;
	static boolean bipartite = true;
	static HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
	static Queue<Integer> que = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = stoi(st.nextToken());
			E = stoi(st.nextToken());
			visit = new boolean[V + 1];
			team = new boolean[V + 1];
			for (int i = 1; i <= V; i++) {
				graph.put(i, new HashSet<Integer>());
			}
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int x = stoi(st.nextToken());
				int y = stoi(st.nextToken());
				graph.get(x).add(y);
				graph.get(y).add(x);
			}
			for (int i = 1; i <= V; i++) {
				bfs(i);
			}
			System.out.println(bipartite ? "YES" : "NO");
			bipartite = true;
			que.clear();
			graph.clear();
		}
	}

	private static void bfs(int start) {
		if (visit[start]) {
			return;
		}
		que.offer(start);
		visit[start] = true;
		team[start] = true;
		while (!que.isEmpty()) {
			int v = que.poll();
			for (int i : graph.get(v)) {
				if (visit[i]) {
					if (team[v] == team[i]) {
						bipartite = false;
						return;
					} else {
						continue;
					}
				}
				if (team[v]) {
					team[i] = false;
					visit[i] = true;
				} else {
					team[i] = true;
					visit[i] = true;
				}
				que.offer(i);
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
