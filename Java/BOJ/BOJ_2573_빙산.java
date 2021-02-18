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

	Node() {
	}

	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_2573_빙산 {
	static int N, M;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] field;
	static int[][] temp;
	static int year;

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
			}
		}

		System.out.println(ice());
	}

	private static int ice() {
		int year = 0;
		while (true) {
			year++;
			temp = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					temp[i][j] = field[i][j];
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (field[i][j] != 0) {
						int cnt = 0;
						for (int k = 0; k < 4; k++) {
							int nx = i + dx[k];
							int ny = j + dy[k];
							if (0 <= nx && nx < N && 0 <= ny && ny < M && field[nx][ny] == 0) {
								cnt++;
							}
						}
						if (field[i][j] < cnt) {
							temp[i][j] = 0;
						} else {
							temp[i][j] = field[i][j] - cnt;
						}
					}
				}
			}
			int mash = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					field[i][j] = temp[i][j];
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (temp[i][j] != 0) {
						dfs(i, j);
						mash++;
					}
				}
			}
			if (mash == 0) {
				return 0;
			} else if (mash >= 2) {
				return year;
			} else {
				continue;
			}
		}
	}

	private static void dfs(int x, int y) {
		temp[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (0 <= nx && nx < N && 0 <= ny && ny < M && temp[nx][ny] != 0) {
				dfs(nx, ny);
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
