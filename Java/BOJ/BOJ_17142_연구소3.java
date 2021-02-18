import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;
	int time;

	Node() {
	}

	Node(int x, int y, int time) {
		this.x = x;
		this.y = y;
		this.time = time;
	}
}

public class BOJ_17142_연구소3 {
	static int N, M;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] field;
	static Queue<Node> que = new LinkedList<>();
	static Stack<Node> stack = new Stack<>();
	static List<Node> virusArr = new ArrayList<>();
	static StringBuffer sb = new StringBuffer();
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		field = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				field[i][j] = stoi(st.nextToken());
				if (field[i][j] == 2) {
					virusArr.add(new Node(i, j, 2));
					field[i][j] = -1;
				}
			}
		}
		virusActive(0, 0);
		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
	}

	private static void virusActive(int pos, int cnt) {
		if (pos == virusArr.size()) {
			if (cnt == M) {
				int ans = bfs();
				if (ans != -1) {
					min = Math.min(min, ans);
				}
			}
			return;
		}
		field[virusArr.get(pos).x][virusArr.get(pos).y] = 2;
		stack.push(virusArr.get(pos));
		virusActive(pos + 1, cnt + 1);

		field[virusArr.get(pos).x][virusArr.get(pos).y] = -1;
		stack.pop();
		virusActive(pos + 1, cnt);

	}

	private static int bfs() {
		for (Node n : stack) {
			que.offer(n);
		}
		int temp[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = field[i][j];
			}
		}
		while (!que.isEmpty()) {
			Node n = que.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (0 <= nx && nx < N && 0 <= ny && ny < N && (temp[nx][ny] == 0 || temp[nx][ny] == -1)) {
					if (temp[nx][ny] == 0) {
						temp[nx][ny] = n.time + 1;
						que.offer(new Node(nx, ny, n.time + 1));
					} else {
						temp[nx][ny] = -2;
						que.offer(new Node(nx, ny, n.time + 1));
					}
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (temp[i][j] == 0) {
					return -1;
				}
				max = Math.max(max, temp[i][j]);
			}
		}
		return max - 2;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
