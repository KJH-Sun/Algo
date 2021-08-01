import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_6497_전력난_크루스칼 {
	static PriorityQueue<Edge> pQ = new PriorityQueue<>();
	static int[] rt;
	static int total;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			total = 0;
			st = new StringTokenizer(br.readLine());
			int m = stoi(st.nextToken());
			int n = stoi(st.nextToken());
			if (m == 0 && n == 0) {
				break;
			}
			rt = new int[m + 1];
			for (int i = 1; i <= m; i++) {
				rt[i] = i;
			}
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int s = stoi(st.nextToken());
				int e = stoi(st.nextToken());
				int w = stoi(st.nextToken());
				pQ.add(new Edge(s, e, w));
				total += w;
			}
			System.out.println(total - Krus());
		}
	}

	private static int Krus() {
		int sum = 0;
		while (!pQ.isEmpty()) {
			Edge e = pQ.poll();
			if (find(e.sv) != find(e.ev)) {
				union(e.sv, e.ev);
				sum += e.w;
			}
		}
		return sum;
	}

	private static void union(int sv, int ev) {
		sv = find(sv);
		ev = find(ev);
		if (sv != ev) {
			rt[sv] = ev;
		}
	}

	private static int find(int sv) {
		return rt[sv] = rt[sv] == sv ? sv : find(rt[sv]);
	}

	static class Edge implements Comparable<Edge> {
		int sv;
		int ev;
		int w;

		Edge(int sv, int ev, int w) {
			this.sv = sv;
			this.ev = ev;
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
