package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 로직 :  아래 / 오른쪽 / 위쪽 / 왼쪽 순서로 갈 수 있는지 확인하고, 
 * 나아갈 수 있으면 dfs로 나아간다. dfs로 나아간 횟수를 체크하여, 출력한다.
 * (로테이션 값이 4이면, 모든 방향을 확인했다는 뜻이므로 후진한다)
 * 
 */

public class BOJ_14503_로봇청소기 {
	static int N, M;
	static Node robot;
	static int[][] map;
	static boolean[][] visit;
	static int cnt = 1;
	static int nowRotate;
	static int[] dx = { 1, 0, -1, 0 }; // 아래 오 위 왼
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		st = new StringTokenizer(br.readLine());
		robot = new Node(stoi(st.nextToken()), stoi(st.nextToken()), (3 - stoi(st.nextToken()))); // 아래 오 위 왼
																									// 순서로 dir
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stoi(st.nextToken());
				if (map[i][j] == 1) {
					visit[i][j] = true;
				}
			}
		}
		clean(robot);
		System.out.println(cnt);

	}

	private static void clean(Node now) {
		visit[now.x][now.y] = true;
		int nx = now.x + dx[now.dir];
		int ny = now.y + dy[now.dir];
		if (0 <= nx && nx < N && 0 <= ny && ny < M && !visit[nx][ny] && map[nx][ny] == 0) {
			nowRotate = 0;
			cnt++;
			clean(new Node(nx, ny, (now.dir + 1) % 4));
			return;
		}
		if (nowRotate == 4) {
			int nnx = now.x + dx[(now.dir + 1) % 4];
			int nny = now.y + dy[(now.dir + 1) % 4];
			if (0 <= nnx && nnx < N && 0 <= nny && nny < M && map[nnx][nny] == 0) {
				nowRotate = 0;
				clean(new Node(nnx, nny, now.dir));
				return;
			}
			return;
		}
		nowRotate++;
		clean(new Node(now.x, now.y, (now.dir + 1) % 4));
	}

	static class Node {
		int x;
		int y;
		int dir;

		Node(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
