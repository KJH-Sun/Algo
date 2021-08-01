import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1261_알고스팟 {
	static int N, M;
	static int[][] map;
	static int[][] visit;
	static int[] dx = new int[] { -1, 1, 0, 0 };
	static int[] dy = new int[] { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = stoi(st.nextToken());
		N = stoi(st.nextToken());
		map = new int[N][M];
		visit = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(visit[i], 100000);
		}
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		if(N==1 && M==1) {
			System.out.println(0);
		}else {
			bfs();
		}

	}

	private static void bfs() {
		Queue<Node> que = new ArrayDeque<>();
		que.offer(new Node(0, 0, 0));
		while (!que.isEmpty()) {
			Node n = que.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (checkOl(nx, ny)) {
					if (map[nx][ny] == 1) {
						if (visit[nx][ny] > n.bCnt + 1) {
							que.offer(new Node(nx, ny, n.bCnt + 1));
							visit[nx][ny] = n.bCnt + 1;
						}
					} else {
						if (visit[nx][ny] > n.bCnt) {
							que.offer(new Node(nx, ny, n.bCnt));
							visit[nx][ny] = n.bCnt;
						}
					}
				}
			}
		}
		System.out.println(visit[N - 1][M - 1]);
	}

	private static boolean checkOl(int nx, int ny) {
		return 0 <= nx && 0 <= ny && nx < N && ny < M;
	}

	static class Node {
		int x;
		int y;
		int bCnt;

		Node(int x, int y, int bCnt) {
			this.x = x;
			this.y = y;
			this.bCnt = bCnt;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
