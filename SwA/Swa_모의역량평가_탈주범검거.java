import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swa_모의역량평가_탈주범검거 {
	static int[] dx = new int[] { 0, 0, -1, 1 }; // 왼0 오1 위2 아래3
	static int[] dy = new int[] { -1, 1, 0, 0 };
	static int[][] direc = new int[][] { { 0 }, { 0, 1, 2, 3 }, // 1번
			{ 2, 3 }, // 2번 위 아래
			{ 0, 1 }, // 3번 좌 우
			{ 1, 2 }, // 4번 위 우
			{ 1, 3 }, // 5번 아래 우
			{ 0, 3 }, // 6번 하 좌
			{ 0, 2 } }; // 7번 상 좌
	static int T, H, W, L;
	static Node fall;
	static int map[][];
	static boolean visit[][];
	static List<Node> exist = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = stoi(st.nextToken());
			W = stoi(st.nextToken());
			fall = new Node(stoi(st.nextToken()), stoi(st.nextToken()), 0);
			L = stoi(st.nextToken());
			map = new int[H][W];
			visit = new boolean[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = stoi(st.nextToken());
				}
			}
			bfs();
			System.out.println("#" + tc + " " + exist.size());
			exist.clear();
		}

	}

	private static void bfs() {
		Queue<Node> que = new ArrayDeque<>();
		que.offer(fall);
		exist.add(fall);
		visit[fall.x][fall.y] = true;
		while (!que.isEmpty()) {
			Node n = que.poll();
			int dir = map[n.x][n.y];
			for (int d : direc[dir]) {
				int nx = n.x + dx[d];
				int ny = n.y + dy[d];
				if (isValid(nx, ny) && !visit[nx][ny] && check(nx, ny, d)) {
					if (n.t + 1 < L) {
						que.add(new Node(nx, ny, n.t + 1));
						exist.add(new Node(nx, ny, n.t + 1));
						visit[nx][ny] = true;
					}
				}
			}
		}
	}

	private static boolean check(int nx, int ny, int dir) {
		int now = map[nx][ny];
		switch (now) {
		case 0:
			return false;
		case 1:
			return true;
		}
		// 왼0 오1 위2 아래3
		// 0이면 1,3,4,5 가능
		// 1이면 1,3,6,7 가능
		// 2이면 1,2,5,6 가능
		// 3이면 1,2,4,7 가능
		switch (dir) {
		case 0:
			return now == 3 || now == 4 || now == 5;
		case 1:
			return now == 3 || now == 6 || now == 7;
		case 2:
			return now == 2 || now == 5 || now == 6;
		case 3:
			return now == 2 || now == 4 || now == 7;
		}
		return false;
	}

	private static boolean isValid(int nx, int ny) {
		return 0 <= nx && 0 <= ny && nx < H && ny < W;
	}

	static class Node {
		int x;
		int y;
		int t;

		Node(int x, int y, int t) {
			this.x = x;
			this.y = y;
			this.t = t;
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
