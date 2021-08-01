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



/*
 * 인접리스트 사용하여 다익스트라
 */


public class BOJ_1162_도로포장 {
	static final int INF = (int) 1e9;
	static int N, M, K;
	static List<Edge> list[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Edge>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = stoi(st.nextToken());
			int e = stoi(st.nextToken());
			int w = stoi(st.nextToken());
			list[s].add(new Edge(e, w));
			list[e].add(new Edge(s, w));
		}
		dijk();
	}

	private static void dijk() {
		PriorityQueue<Node> pQ = new PriorityQueue<>();
		boolean[][] visit = new boolean[N + 1][K + 1];
		pQ.add(new Node(1, 0, 0));
		while (!pQ.isEmpty()) {
			Node n = pQ.poll();
			if (!visit[n.cur][n.pack]) {
				if (n.cur == N) {
					System.out.println(n.cost);
					return;
				}
				for (Edge tmp : list[n.cur]) {
					pQ.add(new Node(tmp.e, n.pack, n.cost + tmp.w));
					if (n.pack < K) {
						pQ.add(new Node(tmp.e, n.pack + 1, n.cost));
					}
				}
			}
			visit[n.cur][n.pack] = true;
		}
	}

	static class Edge {
		int e;
		int w;

		Edge(int e, int w) {
			this.e = e;
			this.w = w;
		}
	}

	static class Node implements Comparable<Node> {
		int cur;
		int pack;
		long cost;

		Node(int cur, int pack, long cost) {
			this.cur = cur;
			this.pack = pack;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.cost, o.cost);
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
