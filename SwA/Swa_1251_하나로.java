import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swa_1251_하나로 {
	static int T, N;
	static List<Edge>[] isL;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = stoi(br.readLine());
			int[] isX = new int[N];
			int[] isY = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				isX[j] = stoi(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				isY[j] = stoi(st.nextToken());
			}
			double E = Double.parseDouble(br.readLine());
			isL = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				isL[i] = new ArrayList<Edge>();
			}
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					double l = Math.pow(isX[i] - isX[j], 2) + Math.pow(isY[i] - isY[j], 2);
					isL[i].add(new Edge(j, l));
					isL[j].add(new Edge(i, l));
				}
			}
			sb.append("#" + tc + " " + Math.round(prim() * E) + "\n");
		}
		System.out.println(sb);
	}

	private static double prim() {
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		Queue<Integer> vQ = new LinkedList<>();
		double sum = 0;
		boolean[] visit = new boolean[N];
		vQ.offer(0);
		while (!vQ.isEmpty()) {
			int nv = vQ.poll();
			for (Edge e : isL[nv]) {
				if (!visit[e.v]) {
					pQ.offer(e);
				}
			}
			visit[nv] = true;
			while (!pQ.isEmpty()) {
				Edge e = pQ.poll();
				if (!visit[e.v]) {
					sum += e.weight;
					vQ.add(e.v);
					break;
				}
			}

		}

		return sum;
	}

	static class Edge implements Comparable<Edge> {
		int v;
		double weight;

		Edge(int v, double weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return (int) (this.weight - o.weight);
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
