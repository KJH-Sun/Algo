import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/*
 * 크루스칼로 MST만들기
 * 
 */
public class BOJ_1197_최소스패닝트리_크루스칼 {
	static PriorityQueue<Edge> pQ = new PriorityQueue<>();
	static int[] rt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = stoi(st.nextToken());
		int e = stoi(st.nextToken());
		rt = new int[v + 1];
		for (int i = 1; i <= v; i++) {
			rt[i] = i;
		}
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = stoi(st.nextToken());
			int end = stoi(st.nextToken());
			int weight = stoi(st.nextToken());
			pQ.add(new Edge(start, end, weight)); // 간선 전부 pQ에 추가
		}
		System.out.println(Krus());
	}
	

	private static int Krus() {
		int sum = 0;
		while (!pQ.isEmpty()) {
			Edge e = pQ.poll();
			if (find(e.sv) == find(e.ev)) { // 간선으 ㅣ시작정점과 끝 정점의 루트노드가 같은경우(이미 연결된 정점들인경우)
				continue;
			} else {
				union(e.sv, e.ev); // 아닌경우 두 정점의 루트노드를 연결해줌
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

	private static int find(int s) {
		return rt[s] = rt[s] == s ? s : find(rt[s]);
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
