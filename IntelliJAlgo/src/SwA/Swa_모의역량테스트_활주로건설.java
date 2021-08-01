import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swa_모의역량테스트_활주로건설 {
	static int[][] map;
	static int N, X;
	static int[] dx = { 1, 0 };
	static int[] dy = { 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		int T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			X = stoi(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = stoi(st.nextToken());
				}
			}
			int res = 0;
			for (int i = 0; i < N; i++) {
				if (solve(0, i, 0)) { // 아래로
					res++;
				}
				if (solve(i, 0, 1)) { // 오른쪽으로
					res++;
				}

			}
			sb.append("#" + tc + " " + res + "\n");
		}
		System.out.println(sb);
	}

	private static boolean solve(int sX, int sY, int dir) {
		boolean[] visited = new boolean[N];
		int[] height = new int[N];

		for (int i = 0; i < N; i++) {
			height[i] = (dir == 1) ? map[sX][sY + i] : map[sX + i][sY];
		}

		for (int i = 0; i < N - 1; i++) {
			if (height[i] == height[i + 1]) {
				continue;
			}

			if (Math.abs(height[i] - height[i + 1]) > 1) {
				return false;
			}

			if (height[i] - 1 == height[i + 1]) {
				for (int j = i + 1; j <= i + X; j++) {
					if (j >= N || visited[j] || height[j] != height[i + 1]) {
						return false;
					}
					visited[j] = true;
				}
			} else if (height[i] + 1 == height[i + 1]) {
				for (int j = i; j > i - X; j--) {
					if (j < 0 || visited[j] || height[j] != height[i]) {
						return false;
					}
					visited[j] = true;
				}
			}

		}
		return true;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
