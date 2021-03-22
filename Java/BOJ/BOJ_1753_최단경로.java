import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1753_최단경로 {
	static int N, M;
	static List<Edge>[] graph;
	static int[] res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		graph = new ArrayList[N + 1];
		res = new int[N + 1];
		Arrays.fill(res, Integer.MAX_VALUE);
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Edge>();
		}
		int k = stoi(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			graph[stoi(st.nextToken())].add(new Edge(stoi(st.nextToken()), stoi(st.nextToken())));
		}
		dijk(k);
		for (int i = 1; i < res.length; i++) {
			if (res[i] == Integer.MAX_VALUE) {
				sb.append("INF\n");
			} else {
				sb.append(res[i]+"\n");
			}
		}
		System.out.println(sb);

	}

	private static void dijk(int sv) {
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		Set<Integer> visit = new HashSet<>();
		pQ.add(new Edge(sv, 0));
		res[sv] = 0;

		while (!pQ.isEmpty()) {
			Edge e = pQ.poll();
			if (visit.contains(e.v)) {
				continue;
			}
			visit.add(e.v);
			for (Edge tmpe : graph[e.v]) {
				if (res[tmpe.v] > res[e.v] + tmpe.weight) {
					res[tmpe.v] = res[e.v] + tmpe.weight;
					pQ.add(new Edge(tmpe.v, res[tmpe.v]));
				}
			}
		}
	}

	static class Edge implements Comparable<Edge> {
		int v;
		int weight;

		Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
