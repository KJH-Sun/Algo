package BFS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;

/*
 * 1. 맵 전체를 확인해서 같은 색이 4개 이상인 블록이 있는지 확인
 * 2. 없으면 반복문 이탈
 * 2-1. 있으면, 그것을 없앰
 * 2-2. 이후 중력에 의해 블록들을 아래로 내림
 * 2-3. 이후 블록이 없을때까지 반복
 * 
 */


public class BOJ_11559_PuyoPuyo {
	static Function<String, Integer> stoi = Integer::parseInt;
	static char[][] map = new char[12][6];
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 12; i++) {
			String s = br.readLine();
			map[i] = s.toCharArray();
		}
		int chain = 0;
		while (true) {
			if (solve()) {
				chain++;
			} else {
				break;
			}
		}
		System.out.println(chain);
	}

	private static boolean solve() {
		List<Node> bombs = new ArrayList<>();
		bombs.addAll(check());
		if (bombs.isEmpty()) {
			return false;
		}
		bomb(bombs);
		drop();
		return true;
	}

	private static void drop() {
		Queue<Character> que = new ArrayDeque<>();
		for (int i = 0; i < 6; i++) {
			for (int j = 11; j >= 0; j--) {
				if (map[j][i] != '.') {
					que.offer(map[j][i]);
					map[j][i] = '.';
				}
			}
			int idx = 11;
			while (!que.isEmpty()) {
				map[idx--][i] = que.poll();
			}
		}
	}

	private static void bomb(List<Node> bombs) {
		for (Node n : bombs) {
			delete(n);
		}
	}

	private static void delete(Node n) {
		Queue<Node> que = new ArrayDeque<>();
		que.offer(n);
		char origin = map[n.x][n.y];
		map[n.x][n.y] = '.';
		while (!que.isEmpty()) {
			Node now = que.poll();
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (isValid(nx, ny) && map[nx][ny] == origin) {
					que.offer(new Node(nx, ny));
					map[nx][ny] = '.';
				}
			}
		}
	}

	private static List<Node> check() {
		boolean[][] visit = new boolean[12][6];
		List<Node> nodes = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (map[i][j] != '.' && !visit[i][j]) {
					if (bfs(i, j, visit)) {
						nodes.add(new Node(i, j));
					}
				}
			}
		}
		return nodes;
	}

	// 4개이상 모였는지 체크해야됨 그거 안됐음
	private static boolean bfs(int x, int y, boolean[][] visit) {
		char origin = map[x][y];
		Queue<Node> que = new ArrayDeque<>();
		que.offer(new Node(x, y));
		visit[x][y] = true;
		int cnt = 1;
		while (!que.isEmpty()) {
			Node n = que.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (isValid(nx, ny) && !visit[nx][ny] && map[nx][ny] == origin) {
					que.offer(new Node(nx, ny));
					cnt++;
					visit[nx][ny] = true;
				}
			}
		}
		if (cnt >= 4) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isValid(int x, int y) {
		return 0 <= x && x < 12 && 0 <= y && y < 6;
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}