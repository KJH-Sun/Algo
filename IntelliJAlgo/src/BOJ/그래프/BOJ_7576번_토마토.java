import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Tomato {
	int x;
	int y;

	Tomato() {
	}

	Tomato(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_7576번_토마토 {
	static int N, M, V;
	static Queue<Tomato> tomatos = new LinkedList<>();
	static Stack<Tomato> first = new Stack<>();
	static int[][] field;
	static StringBuffer sb = new StringBuffer();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	private static void bfs() {
		while (!tomatos.isEmpty()) {
			Tomato t = tomatos.poll();
			for (int i = 0; i < 4; i++) {
				int nx = t.x + dx[i];
				int ny = t.y + dy[i];
				if (0 <= nx && 0 <= ny && nx < N && ny < M && field[nx][ny] == 0) {
					field[nx][ny] = field[t.x][t.y] + 1;
					tomatos.offer(new Tomato(nx, ny));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = stoi(st.nextToken());
		N = stoi(st.nextToken());
		field = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				field[i][j] = stoi(st.nextToken());
				if (field[i][j] == 1) {
					tomatos.offer(new Tomato(i, j));
				}
			}
		}
		bfs();
		int max = Integer.MIN_VALUE;
		boolean all = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (field[i][j] > max) {
					max = field[i][j];
				}
				if (field[i][j] == 0) {
					all = false;
				}
			}
		}
		if (!all) {
			System.out.println(-1);
		} else {
			System.out.println(max - 1);
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
