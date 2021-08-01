package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 1. 입력값을 받아서 이동할수 없는 장소는 -1, 이동 가능한 장소를 0으로 입력받음
 * 2. 이후 bfs로 상하좌우 네방향으로 판을 흔들었을때 파란공과 빨간공의 좌표를 Node객체로 묶어서 bfs로 뿌리고,
 * 3. 방문처리는 4차원 visit배열로하여 r.x,r.y,b.x,b.y로 사용한다.
 */


public class BOJ_13460_구슬탈출2 {
	static int N, M, map[][];
	static boolean visit[][][][]; // 빨xy / 파xy
	static int[] dx = { 0, 0, -1, 1 };// 오른쪽, 왼쪽, 위쪽, 아래쪽(동서북남)
	static int[] dy = { 1, -1, 0, 0 };// 오른쪽, 왼쪽, 위쪽, 아래쪽(동서북남)
	static Queue<Node> que = new ArrayDeque<>();
	static int exitX, exitY;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M][N][M];
		int[] red = new int[2]; // 0이 x 1이 y
		int[] blue = new int[2];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = s.charAt(j);
				switch (c) {
				case '#':
					map[i][j] = -1;
					break;
				case '.':
					map[i][j] = 0;
					break;
				case 'B':
					blue[0] = i;
					blue[1] = j;
					break;
				case 'R':
					red[0] = i;
					red[1] = j;
					break;
				case 'O':
					exitX = i;
					exitY = j;
					break;
				}
			}
		}
		visit[red[0]][red[1]][blue[0]][blue[1]] = true;
		que.offer(new Node(red, blue, 0, false));
		bfs();
		System.out.println(-1);

	}

	private static void bfs() {
		while (!que.isEmpty()) {
			Node n = que.poll();
			if (n.t == 10) {
				break;
			}
			for (int i = 0; i < 4; i++) {
				Node nn = move(n, i);
				if (nn.die) {
					continue;
				}
				if (visit[nn.red[0]][nn.red[1]][nn.blue[0]][nn.blue[1]]) {
					continue;
				}
				que.offer(nn);
				visit[nn.red[0]][nn.red[1]][nn.blue[0]][nn.blue[1]] = true;
			}
		}

	}

	private static Node move(Node n, int dir) { // 오른쪽, 왼쪽, 위쪽, 아래쪽(동서북남)
		int[] tR = new int[2];
		int[] tB = new int[2];
		boolean firstMove = false; // false Red / True Blue
		boolean holeRed = false;
		boolean holeBlue = false;

		switch (dir) { //빨간공이 먼저 움직여야하는지, 파란공이 먼저 움직여야하는지 확인
		case 0:
			if (n.red[1] < n.blue[1]) {
				firstMove = true;
			}
			break;
		case 1:
			if (n.red[1] > n.blue[1]) {
				firstMove = true;
			}
			break;
		case 2:
			if (n.red[0] > n.blue[0]) {
				firstMove = true;
			}
			break;
		case 3:
			if (n.red[0] < n.blue[0]) {
				firstMove = true;
			}
			break;
		}
		// 파란공과 빨간공이 겹치면 위치를 되돌리고 움직이는것을 종료한다.
		// 만약 두 공이 모두 홀에 들어간 경우, 이 노드를 사용하지않는다.
		// 파란공만 들어간경우, 역시 노드를 사용하지않는다.
		// 빨간공만 들어갔을때는 시간을 출력하고 프로그램을 종료한다.
		if (firstMove) { 
			tB[0] = n.blue[0];
			tB[1] = n.blue[1];
			tR[0] = n.red[0];
			tR[1] = n.red[1];
			while (isValid(tB[0], tB[1]) && !(tB[0] == tR[0] && tB[1] == tR[1])) {
				tB[0] += dx[dir];
				tB[1] += dy[dir];
				if (exit(tB[0], tB[1])) {
					holeBlue = true;
				}
			}
			tB[0] -= dx[dir];
			tB[1] -= dy[dir];

			while (isValid(tR[0], tR[1]) && !(tB[0] == tR[0] && tB[1] == tR[1])) {
				tR[0] += dx[dir];
				tR[1] += dy[dir];
				if (exit(tR[0], tR[1])) {
					holeRed = true;
				}
			}
			tR[0] -= dx[dir];
			tR[1] -= dy[dir];
		} else {
			tR[0] = n.red[0];
			tR[1] = n.red[1];
			tB[0] = n.blue[0];
			tB[1] = n.blue[1];
			while (isValid(tR[0], tR[1]) && !(tB[0] == tR[0] && tB[1] == tR[1])) {
				tR[0] += dx[dir];
				tR[1] += dy[dir];
				if (exit(tR[0], tR[1])) {
					holeRed = true;
				}
			}
			tR[0] -= dx[dir];
			tR[1] -= dy[dir];

			while (isValid(tB[0], tB[1]) && !(tB[0] == tR[0] && tB[1] == tR[1])) {
				tB[0] += dx[dir];
				tB[1] += dy[dir];
				if (exit(tB[0], tB[1])) {
					holeBlue = true;
				}
			}
			tB[0] -= dx[dir];
			tB[1] -= dy[dir];
		}
		if (holeBlue) {
			return new Node(tR, tB, n.t + 1, true);
		} else if (holeRed) {
			System.out.println(n.t + 1);
			System.exit(0);
		}
		return new Node(tR, tB, n.t + 1, false);

	}

	private static boolean exit(int x, int y) {
		return x == exitX && y == exitY;
	}

	private static boolean isValid(int nx, int ny) {
		return 0 < nx && 0 < ny && nx < N - 1 && ny < M - 1 && map[nx][ny] == 0;
	}

	static class Node {
		int red[];
		int blue[];
		int t;
		boolean die;

		Node(int[] red, int[] blue, int t, boolean die) {
			this.red = red;
			this.blue = blue;
			this.t = t;
			this.die = die;
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
