import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16326_아기상어 {
	static int N;
	static int[][] map;
	static Shark curPosShark;
//	static List<Fish> fList = new ArrayList<>();
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int eatCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = stoi(st.nextToken());
				if (map[i][j] == 9) {
					curPosShark = new Shark(i, j, 2, 0);
				}
			}
		}
		while (true) {
			if (!bfs()) {
				break;
			}
			if (++eatCnt == curPosShark.size) {
				curPosShark.size++;
				eatCnt = 0;
			}
		}
		System.out.println(curPosShark.time);
	}

	private static boolean bfs() {
		List<Fish> fList = new ArrayList<>();

		PriorityQueue<Shark> que = new PriorityQueue<>(new Comparator<Shark>() {

			@Override
			public int compare(Shark o1, Shark o2) {
				int diff = o1.time - o2.time;
				if (diff == 0) {
					diff = o1.x - o2.x;
				}
				return diff == 0 ? o1.y - o2.y : diff;
			}

		});
		que.offer(curPosShark);
		boolean[][] visit = new boolean[N][N];

		while (!que.isEmpty()) {
			Shark s = que.poll();
			for (int i = 0; i < 4; i++) {
				int nx = s.x + dx[i];
				int ny = s.y + dy[i];
				if (0 <= nx && nx < N && 0 <= ny && ny < N && map[nx][ny] <= s.size && !visit[nx][ny]) {
					if (map[nx][ny] != 0 && map[nx][ny] != 9 && map[nx][ny] < s.size) {
						fList.add(new Fish(nx, ny, s.time + 1));
					}
					que.offer(new Shark(nx, ny, s.size, s.time + 1));
					visit[nx][ny] = true;
				}
			}
		}

		if (fList.isEmpty()) {
			return false;
		} else {
			Collections.sort(fList);
			Fish f = fList.get(0);
			map[curPosShark.x][curPosShark.y] = 0;
			map[f.x][f.y] = 9;
			curPosShark = new Shark(f.x, f.y, curPosShark.size, f.dis);
			return true;
		}
	}

	static class Shark {
		int x;
		int y;
		int size;
		int time;

		Shark(int x, int y, int size, int time) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.time = time;
		}
	}

	static class Fish implements Comparable<Fish> {
		int x;
		int y;
		int dis;

		Fish(int x, int y, int dis) {
			this.x = x;
			this.y = y;
			this.dis = dis;
		}

		@Override
		public int compareTo(Fish o) {
			int diff = this.dis - o.dis;
			if (diff == 0) {
				diff = this.x - o.x;
			}
			return diff == 0 ? this.y - o.y : diff;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
