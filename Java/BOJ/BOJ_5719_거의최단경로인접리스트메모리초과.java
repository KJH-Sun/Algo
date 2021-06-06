import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5719_거의최단경로인접리스트메모리초과 {
	static int[] dist;
	static int N, M, start, end;
	static List<Edge> road[];
	static List<Edge> back[];
	static final int INF = (int) 1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			M = stoi(st.nextToken());
			if (N == 0 && M == 0) {
				break;
			}
			st = new StringTokenizer(br.readLine());
			start = stoi(st.nextToken());
			end = stoi(st.nextToken());
			dist = new int[N];
			road = new ArrayList[N];
			back = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				road[i] = new ArrayList<Edge>();
				back[i] = new ArrayList<Edge>();
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = stoi(st.nextToken());
				int e = stoi(st.nextToken());
				int w = stoi(st.nextToken());
				road[s].add(new Edge(s, e, w));
				back[e].add(new Edge(s, e, w));
			}
			dijk();
			shortcutdelete();
			dijk();

			System.out.println(dist[end] == INF ? -1 : dist[end]);
		}
	}

	private static void dijk() {
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		Arrays.fill(dist, INF);
		dist[start] = 0;
		pQ.offer(new Edge(start, start, 0));

		while (!pQ.isEmpty()) {
			Edge cur = pQ.poll();
			if (cur.w > dist[cur.e]) {
				continue;
			}
			for (Edge check : road[cur.e]) {
				if (dist[check.e] > dist[cur.e] + check.w) {
					dist[check.e] = dist[cur.e] + check.w;
					pQ.add(new Edge(cur.e, check.e, dist[check.e]));
				}
			}
		}
	}

	private static void shortcutdelete() {
		Queue<Integer> que = new ArrayDeque<>();
		List<Edge> deleteList = new ArrayList<>();
		que.offer(end);
		while (!que.isEmpty()) {
			int cur = que.poll();
			if (cur == start) {
				continue;
			}
			for (Edge check : back[cur]) {
				if (dist[cur] == dist[check.s] + check.w) {
					deleteList.add(check);
					que.add(check.s);
				}
			}
		}
		for (Edge e : deleteList) {
			road[e.s].remove(e);
		}
	}

	static class Edge implements Comparable<Edge> {
		int s;
		int e;
		int w;

		Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}

		@Override
		public boolean equals(Object obj) {
			Edge e = (Edge) obj;
			return this.s == e.s && this.e == e.e && this.w == e.w;
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
