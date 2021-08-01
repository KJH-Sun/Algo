import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * V개의 노드가 있고 e개의 간선이 있다면, [V-1]번 e개의 간선에 대해 dist[]를 갱신해준다.
 * 그러고나서 1번 e개의 간선에 대해 dist[]를 갱신하려할때 갱신된다면 음의 사이클이 존재한다는 뜻.(영구히 갱신된다는 뜻)
 * 만약 dist의 값이 INF라면, 해당 경로가 없다는 것을 의미한다.
 * 출력초과나면 INF를 long으로 변경하자.
 * 
 * 나쁜 민식선생님...
 * 1. 음의 사이클이 있는지 확인한다
 * 2. 음의 사이클이 최종도착지를 포함하는지 bfs로 확인한다
 * 포함한다면 Gee
 * 포함하지않는다면 정상출력
 * 음의 사이클이 없다면 정상출력
 * 애초에 최종 도착지에 도달을 못하면 gg
 */

public class BOJ_1219_오민식의고민 {
	static int N, SC, EC, M;
	static long[] dist;
	static int[] income;
	static final long INF = (long) 1e18;
	static List<List<Edge>> line;
	static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		SC = stoi(st.nextToken());
		EC = stoi(st.nextToken());
		M = stoi(st.nextToken());
		dist = new long[N + 1];
		Arrays.fill(dist, INF);

		line = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			line.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = stoi(st.nextToken());
			int end = stoi(st.nextToken());
			int weight = stoi(st.nextToken());
			line.get(start).add(new Edge(end, weight));
		}
		income = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			income[i] = stoi(st.nextToken()) * -1;
		}

		String res = "";
		if (!bfs(SC, EC)) {
			res = "gg";
		} else {
			if (bellmanFord()) {
				res = "Gee";
			} else {
				res = String.valueOf(dist[EC] * -1);
			}
		}
		System.out.println(res);
	}

	private static boolean bellmanFord() {
		dist[SC] = income[SC];
		boolean update = false;
		for (int i = 0; i < N - 1; i++) {
			update = false;
			for (int j = 0; j < N; j++) {
				for (Edge e : line.get(j)) {
					if (dist[j] != INF && dist[e.e] > dist[j] + e.t + income[e.e]) {
						dist[e.e] = dist[j] + e.t + income[e.e];
						update = true;
					}
				}
			}
			if (!update) {
				break;
			}
		}
		List<Integer> cycle = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (Edge e : line.get(i)) {
				if (dist[i] != INF && dist[e.e] > dist[i] + e.t + income[e.e]) {
					cycle.add(i);
					cycle.add(e.e);
				}
			}
		}

		for (int i : cycle) {
			if (bfs(i, EC)) {
				return true;
			}
		}
		// 사이클과 목적지가 관계가 없는 경우, dist배열을 초기화하고 벨만포드 알고리즘 한번 더 수행(싸이클을 이루는 노드는 제외한채로)
		Arrays.fill(dist, INF);
		dist[SC] = income[SC];
		for (int i = 0; i < N - 1; i++) {
			update = false;
			for (int j = 0; j < N; j++) {
				for (Edge e : line.get(j)) {
					if (dist[j] != INF && dist[e.e] > dist[j] + e.t + income[e.e]) {
						dist[e.e] = dist[j] + e.t + income[e.e];
						update = true;
					}
				}
			}
			if (!update) {
				break;
			}
		}

		return false;

	}

	private static boolean bfs(int start, int end) {
		if (start == end) {
			return true;
		}
		Queue<Integer> que = new ArrayDeque<>();
		boolean[] visit = new boolean[N];
		visit[start] = true;
		que.offer(start);

		while (!que.isEmpty()) {
			int cur = que.poll();
			for (Edge e : line.get(cur)) {
				if (!visit[e.e]) {
					if (e.e == end) {
						return true;
					}
					visit[e.e] = true;
					que.offer(e.e);
				}
			}
		}
		return false;
	}

	static class Edge {
		int e;
		int t;

		Edge(int e, int t) {
			this.e = e;
			this.t = t;
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
