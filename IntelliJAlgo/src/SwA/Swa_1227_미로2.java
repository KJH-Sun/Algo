import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swa_1227_미로2 {
	static int T;
	static int[][] map = new int[100][100];
	static int res;
	static Node start;
	static Queue<Node> que = new LinkedList<>();
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		for (int tc = 1; tc <= 10; tc++) {
			T = stoi(br.readLine());
			for (int i = 0; i < 100; i++) {
				String s = br.readLine();
				for (int j = 0; j < 100; j++) {
					map[i][j] = s.charAt(j)-'0';
					if (map[i][j] == 2) {
						start = new Node(i, j);
					}
				}
			}
			if (bfs()) {
				sb.append("#" + tc + " " + 1 + "\n");
			} else {
				sb.append("#" + tc + " " + 0 + "\n");
			}
			que.clear();
		}
		System.out.println(sb);
	}

	private static boolean bfs() {
		que.offer(new Node(start.x, start.y));

		while (!que.isEmpty()) {
			Node n = que.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (0 <= nx && nx < 100 && 0 <= ny && ny < 100 && (map[nx][ny] == 0 || map[nx][ny] == 3)) {
					if (map[nx][ny] == 3) {
						return true;
					}
					map[nx][ny] = 2;
					que.offer(new Node(nx, ny));
				}
			}
		}
		return false;
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
