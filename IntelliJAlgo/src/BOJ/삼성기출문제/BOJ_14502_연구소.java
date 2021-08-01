package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
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

public class BOJ_14502_연구소 {
	static int N, M, R;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	static int[][] field;
	static int max = Integer.MIN_VALUE;
	static Queue<Node> virusQue = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		field = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				field[i][j] = stoi(st.nextToken());
				if (field[i][j] == 2) {
					virusQue.offer(new Node(i, j));
				}
			}
		}
		createWall(0, 0, 0);
		System.out.println(max);
	}

	private static void virus() {
		int[][] temp = new int[N][M];
		Queue<Node> tempQue = new ArrayDeque<>();

		deepcopy(temp, field);
		for (Node n : virusQue) {
			tempQue.offer(n);
		}
		while (!tempQue.isEmpty()) {
			Node n = tempQue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (0 <= nx && nx < N && 0 <= ny && ny < M && field[nx][ny] == 0) {
					field[nx][ny] = 2;
					tempQue.offer(new Node(nx, ny));
				}
			}
		}
		max = Math.max(max, checkSafety());
		deepcopy(field, temp);
	}

	private static int checkSafety() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (field[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void deepcopy(int[][] dest, int[][] src) {
		for (int i = 0; i < dest.length; i++) {
			for (int j = 0; j < dest[i].length; j++) {
				dest[i][j] = src[i][j];
			}
		}
	}

	private static void createWall(int posX, int posY, int cnt) {
		if (cnt == 3) {
			virus();
			return;
		}
		if (posX == N) {
			return;
		}
		if (posY >= M) {
			createWall(posX + 1, 0, cnt);
			return;
		}
		if (field[posX][posY] == 0) {
			field[posX][posY] = 1;
			createWall(posX, posY + 1, cnt + 1);
			field[posX][posY] = 0;
		}
		createWall(posX, posY + 1, cnt);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
