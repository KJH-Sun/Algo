import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7562_나이트의이동 {
	static int T, L;
	static boolean visit[][];
	static int max = Integer.MIN_VALUE;
	static int[] dx = { 1, 1, -1, -1, 2, 2, -2, -2 }; // 동서남북위아래
	static int[] dy = { 2, -2, 2, -2, 1, -1, 1, -1 };
	static int[] dh = { 0, 0, 0, 0, 1, -1 };

	static Queue<Node> que = new LinkedList<>();
	static Node end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			L = stoi(br.readLine());
			visit = new boolean[L][L];
			st = new StringTokenizer(br.readLine());
			que.offer(new Node(stoi(st.nextToken()), stoi(st.nextToken()), 0));
			st = new StringTokenizer(br.readLine());
			end = new Node(stoi(st.nextToken()), stoi(st.nextToken()), 0);
			System.out.println(bfs());
			que.clear();
		}
	}

	private static int bfs() {
		while (!que.isEmpty()) {
			Node n = que.poll();
			if (n.x == end.x && n.y == end.y) {
				return n.time;
			}
			for (int i = 0; i < 8; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (0 <= nx && nx < L && 0 <= ny && ny < L && !visit[nx][ny]) {
					visit[nx][ny] = true;
					que.offer(new Node(nx, ny, n.time + 1));
				}
			}
		}
		return -1;
	}

	static class Node {
		int x;
		int y;
		int time;

		Node(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
