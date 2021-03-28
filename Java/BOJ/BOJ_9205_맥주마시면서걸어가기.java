import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_9205_맥주마시면서걸어가기 {
	static int T;
	static int posi = 1000;
	static Node[] points;
	static int arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = stoi(br.readLine());
			points = new Node[N + 2];
			arr = new int[N + 2];
			for (int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i] = i;
				points[i] = new Node(stoi(st.nextToken()), stoi(st.nextToken()));
			}
			solve(N);
			if (find(0) == find(N + 1)) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}
		}

	}

	private static void solve(int n) {
		for (int i = 0; i < n + 2; i++) {
			for (int j = i + 1; j < n + 2; j++) {
				int dis = Math.abs(points[i].x - points[j].x) + Math.abs(points[i].y - points[j].y);
				if (dis <= 1000) {
					union(i, j);
				}
			}
		}
	}

	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u == v) {
			return;
		}
		arr[u] = v;
	}

	private static int find(int u) {
		return arr[u] = arr[u] == u ? u : find(arr[u]);
	}

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
