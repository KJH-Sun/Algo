import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_1260_DFS와BFS {
	static HashMap<Integer, TreeSet> graph = new HashMap<>();
	static Queue<Integer> que = new LinkedList<>();
	static int N, M, V;
	static Stack<Integer> res = new Stack<>();
	static boolean[] visit;
	static StringBuffer sb = new StringBuffer();

	static void dfs(int v) {
		if (!visit[v]) {
			sb.append(v + " ");
			visit[v] = true;
			Iterator iter = graph.get(v).iterator();
			while (iter.hasNext()) {
				dfs((Integer) iter.next());
			}
		}

	}

	private static void bfs(int v) {
		sb.append(v + " ");
		visit[v] = true;
		Iterator iter = graph.get(v).iterator();
		while (iter.hasNext()) {
			que.offer((Integer) iter.next());
		}
		while (!que.isEmpty()) {
			int x = que.poll();
			if (!visit[x]) {
				iter = graph.get(x).iterator();
				while (iter.hasNext()) {
					que.offer((Integer) iter.next());
				}
				visit[x] = true;
				sb.append(x + " ");
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		V = stoi(st.nextToken());
		visit = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			graph.put(i, new TreeSet<Integer>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken());
			int y = stoi(st.nextToken());
			graph.get(x).add(y);
			graph.get(y).add(x);
		}
		dfs(V);
		visit = new boolean[N + 1];
		sb.append("\n");
		bfs(V);
		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
