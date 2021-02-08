import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2644_촌수계산 {
	static int N, Pa, Ch, M;
	static Queue<Integer> que = new LinkedList<>();
	static HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
	static boolean[] visit;
	static int[] time;

	private static int bfs() {
		que.offer(Pa);
		while (!que.isEmpty()) {
			int x = que.poll();
			if (x == Ch) {
				return time[x];
			}
			if (!visit[x]) {
				visit[x] = true;
				for (int v : graph.get(x)) {
					if (!visit[v]) {
						que.offer(v);
						time[v] = time[x] + 1;
					}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Pa = stoi(st.nextToken());
		Ch = stoi(st.nextToken());
		M = stoi(br.readLine());
		for (int i = 1; i <= N; i++) {
			graph.put(i, new HashSet<Integer>());
		}
		visit = new boolean[N + 1];
		time = new int[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken());
			int y = stoi(st.nextToken());
			graph.get(x).add(y);
			graph.get(y).add(x);
		}
		System.out.println(bfs());

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
