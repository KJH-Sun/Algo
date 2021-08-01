package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;

/*
 * 15591번 MooTube
 * 각 정점을 기준점으로 모든 정점의 유사도를 구하는 dp 배열을 만든 다음, dp배열을 순회하며 K이상인 수를 카운트해서 출력
 * 
 */

public class BOJ_15591_MooTube {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int N, Q;
	static List<Edge> edges[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi.apply(st.nextToken());
		Q = stoi.apply(st.nextToken());
		edges = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = stoi.apply(st.nextToken());
			int e = stoi.apply(st.nextToken());
			int l = stoi.apply(st.nextToken());
			edges[s].add(new Edge(e, l));
			edges[e].add(new Edge(s, l));
		}
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			System.out.println(check(stoi.apply(st.nextToken()), stoi.apply(st.nextToken())));
		}
	}

	private static int check(int k, int s) {
		int[] dp = new int[N + 1];
		boolean[] visit = new boolean[N + 1];
		dp[s] = Integer.MAX_VALUE;
		visit[s] = true;
		Queue<Integer> que = new ArrayDeque<>();
		que.add(s);
		while (!que.isEmpty()) {
			int n = que.poll();
			visit[n] = true;
			for (Edge e : edges[n]) {
				if (visit[e.e]) {
					continue;
				}
				dp[e.e] = Math.min(dp[n], e.l);
				que.add(e.e);
			}
		}
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (i == s) {
				continue;
			}
			if (dp[i] >= k) {
				cnt++;
			}
		}
		return cnt;
	}

	static class Edge {
		int e;
		int l;

		Edge(int e, int l) {
			this.e = e;
			this.l = l;
		}
	}

}