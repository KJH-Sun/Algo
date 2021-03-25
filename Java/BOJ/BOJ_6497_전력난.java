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

public class BOJ_6497_전력난 {
	static int M, N;
	static List<Edge>[] load;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = stoi(st.nextToken()); // 집의 수
			N = stoi(st.nextToken()); // 길의 수
			if (M == 0 && N == 0) {
				break;
			}
			load = new ArrayList[M + 1];
			for (int i = 0; i < M; i++) {
				load[i] = new ArrayList<>();
			}
			int totalDis = 0;
			;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int s = stoi(st.nextToken());
				int e = stoi(st.nextToken());

				int w = stoi(st.nextToken());
				load[s].add(new Edge(e, w));
				load[e].add(new Edge(s, w));
				totalDis += w;
			}
			System.out.println(totalDis - prim());
		}

	}

	private static int prim() {
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		Queue<Integer> hQ = new LinkedList<>();
		boolean[] visit = new boolean[M + 1];
		List<Edge> MST = new ArrayList<>();

		hQ.offer(0);
		while (!hQ.isEmpty()) {
			int h = hQ.poll();
			for (Edge e : load[h]) {
				if (!visit[e.v]) {
					pQ.offer(e);
				}
			}
			visit[h] = true;
			while (!pQ.isEmpty()) {
				Edge e = pQ.poll();
				if (!visit[e.v]) {
					hQ.add(e.v);
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
		int v;
		int weight;

		Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
