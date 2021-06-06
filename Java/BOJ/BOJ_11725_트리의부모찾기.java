import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11725_트리의부모찾기 {
	static int N;
	static int[] root;
	static List<Integer> line[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		N = stoi(br.readLine());
		StringTokenizer st;
		line = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			line[i] = new ArrayList<>();
		}
		root = new int[N + 1];
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = stoi(st.nextToken());
			int e = stoi(st.nextToken());
			line[s].add(e);
			line[e].add(s);
		}
		makeTree();
		for (int i = 2; i <= N; i++) {
			sb.append(root[i] + "\n");
		}
		System.out.println(sb);

	}

	private static void makeTree() {
		Queue<Integer> que = new ArrayDeque<>();
		que.offer(1);
		root[1] = 1;
		while (!que.isEmpty()) {
			int now = que.poll();
			for (int child : line[now]) {
				if (root[child] == 0) {
					root[child] = now;
					que.offer(child);
				}
			}
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
