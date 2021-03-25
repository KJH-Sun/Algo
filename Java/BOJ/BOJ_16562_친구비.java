import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_16562_친구비 {
	static int N, M, K;
	static int[] map;
	static int[] fp;
	static Set<Integer> friend = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		st = new StringTokenizer(br.readLine());
		fp = new int[N + 1];
		map = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			fp[i] = stoi(st.nextToken());
			map[i] = i;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = stoi(st.nextToken());
			int v = stoi(st.nextToken());
			union(u, v);
		}
		friend.add(find(map[1]));
		for (int i = 2; i <= N; i++) {
			if (!friend.contains((Integer) find(map[i]))) {
				friend.add(find(map[i]));
			}
		}
		int sum = 0;
		for (int i : friend) {
			sum += fp[i];
		}
		if (sum > K) {
			System.out.println("Oh no");
		} else {
			System.out.println(sum);
		}

	}

	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u == v) {
			return;
		}
		if (fp[u] >= fp[v]) {
			map[u] = v;
		} else {
			map[v] = u;
		}
	}

	private static int find(int u) {
		return map[u] = map[u] == u ? u : find(map[u]);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
