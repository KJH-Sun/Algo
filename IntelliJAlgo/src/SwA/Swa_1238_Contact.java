import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Swa_1238_Contact {
	static Map<Integer, Set<Integer>> graph = new HashMap<>();
	static int N, root;
	static int[] time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			graph.clear();
			time = new int[101];
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			root = stoi(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int key = stoi(st.nextToken());
				if (graph.containsKey(key)) {
					graph.get(key).add(stoi(st.nextToken()));
				} else {
					graph.put(key, new HashSet<Integer>());
					graph.get(key).add(stoi(st.nextToken()));
				}
			}
			bfs();
			int result = 0;
			int max = Integer.MIN_VALUE;
			for (int i = 1; i <= 100; i++) {
				if (max <= time[i]) {
					max = time[i];
					result = i;
				}
			}
			System.out.println("#" + tc + " " + result);
		}

	}

	private static void bfs() {
		Queue<Integer> que = new LinkedList<>();
		que.offer(root);

		while (!que.isEmpty()) {
			int v = que.poll();
			if (!graph.containsKey(v)) {
				continue;
			} else {
				for (int i : graph.get(v)) {
					if (time[i] != 0) {
						continue;
					}
					time[i] = time[v] + 1;
					que.offer(i);
				}
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
