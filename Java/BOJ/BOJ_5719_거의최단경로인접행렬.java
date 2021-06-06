import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_5719_거의최단경로인접행렬 {
	static int[] dist;
	static int[][] map;
	static int N, M, start, end;
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
			map = new int[N][N];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				map[stoi(st.nextToken())][stoi(st.nextToken())] = stoi(st.nextToken());
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
		pQ.offer(new Edge(start, 0));

		while (!pQ.isEmpty()) {
			Edge cur = pQ.poll();
			if (cur.w > dist[cur.e]) {
				continue;
			}
			for (int i = 0; i < N; i++) {
				if (map[cur.e][i] == 0) {
					continue;
				}
				if (dist[i] > dist[cur.e] + map[cur.e][i]) {
					dist[i] = dist[cur.e] + map[cur.e][i];
					pQ.add(new Edge(i, dist[i]));
				}
			}
		}
		shortcutdelete();
	}

	private static void shortcutdelete() {
		Queue<Integer> que = new ArrayDeque<>();
		que.offer(end);
		while (!que.isEmpty()) {
			int cur = que.poll();
			for (int i = 0; i < N; i++) {
				if (map[i][cur] == 0) {
					continue;
				}
				if (dist[cur] == dist[i] + map[i][cur]) {
					map[i][cur] = 0;
					que.add(i);
				}
			}
		}
	}

	static class Edge implements Comparable<Edge> {
		int e;
		int w;

		Edge(int e, int w) {
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
