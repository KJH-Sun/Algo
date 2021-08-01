import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;


/*
 * 프림으로 MST
 * 
 */
public class BOJ_1197_최소스패닝트리 {
	static int V, E;
	static List<Edge>[] eList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = stoi(st.nextToken());
		E = stoi(st.nextToken());
		eList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			eList[i] = new ArrayList<Edge>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = stoi(st.nextToken());
			int e = stoi(st.nextToken());
			int w = stoi(st.nextToken());
			eList[s].add(new Edge(e, w));
			eList[e].add(new Edge(s, w));
		}
		System.out.println(prim(1));
	}

	private static int prim(int N) {
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		Queue<Integer> vQue = new LinkedList<>();
		List<Edge> MST = new ArrayList<>();
		Set<Integer> visit = new HashSet<>();
		vQue.offer(N);
		while (!vQue.isEmpty()) {
			int v = vQue.poll();
			visit.add(v);
			for (Edge e : eList[v]) {
				if (!visit.contains((Integer) e.ev)) {
					pQ.add(e);
				}
			}
			while (!pQ.isEmpty()) {
				Edge e = pQ.poll();
				if (!visit.contains((Integer) e.ev)) {
					vQue.offer(e.ev);
					MST.add(e);
					break;
				}
			}
		}
		int sum = 0;
		for (Edge e : MST) {
			sum += e.weight;
		}
		return sum;
	}

	static class Edge implements Comparable<Edge> {
		int ev;
		int weight;

		Edge(int ev, int weight) {
			this.ev = ev;
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
