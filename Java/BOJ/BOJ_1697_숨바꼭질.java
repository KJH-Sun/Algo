import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697_숨바꼭질 {
	static int N, K;
	static Queue<Integer> que = new LinkedList<>();
	static int MAX = 100001;
	static int[] time = new int[MAX];

	private static int bfs() {
		que.offer(N);
		while (!que.isEmpty()) {
			int x = que.poll();
			if (x == K) {
				return time[x];
			}
			for (int v : new int[] { x - 1, x + 1, x * 2 }) {
				if (0 <= v && v < MAX && time[v] == 0) {
					que.offer(v);
					time[v] = time[x] + 1;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		K = stoi(st.nextToken());
		System.out.println(bfs());

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
