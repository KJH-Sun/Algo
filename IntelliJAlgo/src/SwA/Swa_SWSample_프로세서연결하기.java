import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swa_SWSample_프로세서연결하기 {
	static int T, N;
	static int[][] map;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static List<Node> list = new ArrayList<>();
	static int maxCore;
	static int minLine;

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = stoi(br.readLine());
			map = new int[N][N];
			maxCore = Integer.MIN_VALUE;
			minLine = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = stoi(st.nextToken());
					if (map[i][j] == 1 && (i == 0 || i == N - 1 || j == 0 || j == N - 1)) {
						continue;
					} else if (map[i][j] == 1) {
						list.add(new Node(i, j));
					}
				}
			}
			solve(0, 0);
			sb.append("#" + tc + " " + minLine + "\n");
			list.clear();

		}
		System.out.println(sb);
	}

	private static void solve(int index, int success) {
		if (index == list.size()) {
			if (success == maxCore) {
				maxCore = Math.max(maxCore, success);
				int minL = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j] == 2) {
							minL++;
						}
					}
				}
				minLine = Math.min(minLine, minL);
			} else if (success > maxCore) {
				maxCore = Math.max(maxCore, success);
				int minL = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j] == 2) {
							minL++;
						}
					}
				}
				minLine = minL;
			}
			return;
		}
		int[][] temp = new int[N][N];
		deepcopy(temp, map);
		Node n = list.get(index);
		for (int j = 0; j < 4; j++) {
			int nx = n.x;
			int ny = n.y;
			while (true) {
				nx += dx[j];
				ny += dy[j];
				if (0 <= nx && nx < N && 0 <= ny && ny < N && map[nx][ny] == 0) {
					map[nx][ny] = 2;
				} else if (0 <= nx && nx < N && 0 <= ny && ny < N) {
					deepcopy(map, temp);
					break;
				} else {
					solve(index + 1, success + 1);
					deepcopy(map, temp);
					break;
				}
			}
		}
		solve(index + 1, success);

	}

	private static void deepcopy(int[][] a, int[][] b) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				a[i][j] = b[i][j];
			}
		}
	}

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}
