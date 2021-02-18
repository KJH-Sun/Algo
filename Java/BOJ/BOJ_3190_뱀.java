import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
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

	public boolean equals(Node n) {
		if (this.x == n.x && this.y == n.y) {
			return true;
		} else {
			return false;
		}
	}
}

public class BOJ_3190_뱀 {
	static int N, K, L;
	static int[][] field;
	static int time;
	static int[] dx = { 0, 1, 0, -1 }; // 우0하1좌2상3
	static int[] dy = { 1, 0, -1, 0 };
	static int direc;
	static Node head;
	static Queue<Node> snake = new LinkedList<>();
	static StringBuffer sb = new StringBuffer();
	static HashMap<Integer, Character> dir = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = stoi(br.readLine());
		K = stoi(br.readLine());
		field = new int[N][N];
		field[0][0] = 1;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken());
			int y = stoi(st.nextToken());
			field[x - 1][y - 1] = 2;
		}
		L = stoi(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken());
			char c = st.nextToken().charAt(0);
			dir.put(x, c);
		}
		head = new Node(0, 0);
		snake.offer(new Node(0, 0));
		move();
		System.out.println(time);
	}

	private static void move() {
		while (true) {
			time++;
			for (int i : dir.keySet()) {
				if (time - 1 == i) {
					switch (dir.get(i)) {
					case 'D': // 오른쪽
						direc++;
						if (direc >= 4) {
							direc -= 4;
						}
						break;
					case 'L': // 왼쪽
						direc--;
						if (direc < 0) {
							direc += 4;
						}
						break;
					}
				}
			}

			int nx = head.x + dx[direc];
			int ny = head.y + dy[direc];
			if (0 > nx || 0 > ny || nx >= N || ny >= N) {
				return;
			}
			switch (field[nx][ny]) {
			case 0:
				field[nx][ny] = 1;
				head = new Node(nx, ny);
				snake.offer(new Node(nx, ny));
				Node n = snake.poll();
				field[n.x][n.y] = 0;
				break;
			case 1:
				return;
			case 2:
				field[nx][ny] = 1;
				head = new Node(nx, ny);
				snake.offer(new Node(nx, ny));
				break;
			}

		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
