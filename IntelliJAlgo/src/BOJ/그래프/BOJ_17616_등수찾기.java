package 그래프;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;

/*
 * X보다 큰 수, 작은 애를 따로 체크한 다음
 * U는 전체 수 - X보다 큰 수
 * V는 전체 수 - X보다 작은 수 + 모르는 수
 */

public class BOJ_17616_등수찾기 {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int N, M, X, U, V;
	static Map<Integer, List<Integer>> high = new HashMap<>(); // 나보다 높은 등수
	static Map<Integer, List<Integer>> low = new HashMap<>(); // 나보다 낮은 등수
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi.apply(st.nextToken());
		M = stoi.apply(st.nextToken());
		X = stoi.apply(st.nextToken());
		visit = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			high.putIfAbsent(i, new ArrayList<Integer>());
			low.putIfAbsent(i, new ArrayList<Integer>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s1 = stoi.apply(st.nextToken());
			int s2 = stoi.apply(st.nextToken());
			high.get(s2).add(s1);
			low.get(s1).add(s2);
		}
		U = bfs(high) + 1;
		V = N - bfs(low);
		System.out.println(U + " " + V);
	}

	private static int bfs(Map<Integer, List<Integer>> map) {
		int sum = 0;
		Queue<Integer> que = new ArrayDeque<>();
		boolean[] visit = new boolean[N + 1];
		visit[X] = true;
		que.offer(X);
		while (!que.isEmpty()) {
			int n = que.poll();
			for (int i : map.get(n)) {
				if (!visit[i]) {
					que.add(i);
					visit[i] = true;
					sum++;
				}
			}
		}

		return sum;
	}

}