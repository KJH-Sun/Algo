import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 인접리스트로 pQ 사용하여 다익스트라.
 * 이렇게 사용하는 경우 시간 복잡도 O(V*E)
 * 인접행렬로 사용하는 경우엔 O(V^2)
 * 
 * 다익스트라
 * 두가지 풀이방식이 존재합니다
 * 인접행렬 / 인접리스트(내가 주로 많이 사용하는 방식)
 * O(V^2*E) / O(V*E)
 * V는 정점의 개수
 * E는 간선의 개수
 */


public class BOJ_1753_최단경로 {
	static int N, M;
	static List<Edge>[] graph; 
	static int[] dist; // 0번 1번 최단거리dist[1] 2번 dist[2]

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		graph = new ArrayList[N + 1];
		dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE); // INF
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Edge>();
		}
		int k = stoi(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// graph[0].add(new Edge(3, 5));
			// 0 3 5  0->3번으로 가는 가중치가 5인 간선이 있다
			// graph[start].add(new Edge(end, weight));
			// graph[end].add(new Edge(start, weight));
			graph[stoi(st.nextToken())].add(new Edge(stoi(st.nextToken()), stoi(st.nextToken())));
		}
		dijk(k);
		for (int i = 1; i < dist.length; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				sb.append("INF\n");
			} else {
				sb.append(dist[i]+"\n");
			}
		}
		System.out.println(sb);

	}

	private static void dijk(int sv) {
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		Set<Integer> visit = new HashSet<>();
		pQ.add(new Edge(sv, 0));
		dist[sv] = 0;
		
		// 1. 시작정점을 pQ에 넣는다(가중치 0으로)
		// 2. 원래는 정점의 개수가 V (V-1번만큼 처음부터 끝까지 순회하면서 dist배열을 갱신) // V^2*E // pQ 사용시 V*E
		// 3. dist[tmpe.v] > dist[e.v] + tmpe.weight
		// dist[2] = 1; 
		// 2-> 3 1짜리 간선 0->2->3 // 0->3
		// dist[3] = 2;
		//                    현재위치까지 오는 최소소모값
		
		
		// 4. 0->목적지 > 0->경유지를 통해서 가는게 더 빠른가
		
		
		while (!pQ.isEmpty()) { // V*E
			Edge e = pQ.poll();
			if (visit.contains(e.v)) {
				continue;
			}
			visit.add(e.v);// 방문했다 (0번)
			for (Edge tmpe : graph[e.v]) { // 가중치가 작은 순서로 pQ에서 나오니까
				if (dist[tmpe.v] > dist[e.v] + tmpe.weight) {
					dist[tmpe.v] = dist[e.v] + tmpe.weight;
					pQ.add(new Edge(tmpe.v, dist[tmpe.v]));
				}
			}
		}
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
			return this.weight - o.weight;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
