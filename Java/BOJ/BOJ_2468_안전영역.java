import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;

	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_2468_안전영역 {
	static int N;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] field;
	static int max = Integer.MIN_VALUE;
	static int maxHeight = Integer.MIN_VALUE;
	static Queue<Node> que = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = stoi(br.readLine());
		field = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				field[i][j] = stoi(st.nextToken());
				maxHeight = Math.max(maxHeight, field[i][j]);
			}
		}
		water();
		System.out.println(max);
	}

	private static void water() {
		int[][] temp = new int[N][N];
		ArrayList<Node> nodeArr = new ArrayList<>();
		for (int waterH = 0; waterH <= maxHeight; waterH++) {
			que.clear();
			nodeArr.clear();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (field[i][j] > waterH) {
						temp[i][j] = 1;
						nodeArr.add(new Node(i, j));
					} else {
						temp[i][j] = 0;
					}
				}
			}
			int cnt = 0;
			for (Node f : nodeArr) {
				if (temp[f.x][f.y] == 1) {
					que.offer(f);
					cnt++;
				}
				while (!que.isEmpty()) {
					Node o = que.poll();
					for (int i = 0; i < 4; i++) {
						int nx = o.x + dx[i];
						int ny = o.y + dy[i];
						if (0 <= nx && nx < N && 0 <= ny && ny < N && temp[nx][ny] == 1) {
							temp[nx][ny] = temp[o.x][o.y] + 1;
							que.offer(new Node(nx, ny));
						}
					}
				}
			}
			max = Math.max(max, cnt);
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
