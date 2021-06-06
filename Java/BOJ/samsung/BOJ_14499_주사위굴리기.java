package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14499_주사위굴리기 {
	static int N, M, K, map[][];
	static Node cube;
	static Queue<Integer> order = new ArrayDeque<>();
	static int[] dx = { 0, 0, -1, 1 };// 오른쪽, 왼쪽, 위쪽, 아래쪽(동서북남)
	static int[] dy = { 1, -1, 0, 0 };// 오른쪽, 왼쪽, 위쪽, 아래쪽(동서북남)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		map = new int[N][M];
		cube = new Node(stoi(st.nextToken()), stoi(st.nextToken()));
		K = stoi(st.nextToken()); // 안쓰는데 혹시몰라서 받아둠
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			order.add(stoi(st.nextToken()));
		}
		move();
	}

	private static void move() {
		while (!order.isEmpty()) {
			int m = order.poll();
			int nx = cube.x + dx[m - 1];
			int ny = cube.y + dy[m - 1];
			if (!isValid(nx, ny)) {
				continue;
			}
			cube.x = nx;
			cube.y = ny;
			// 주사위 방향 돌린 후 큐브 바닥면과 지도 숫자 확인해서 복사하고 상단숫자 출력
			switch (m) {
			case 1: // 동 (왼쪽면에 있던걸 탑으로)
				cube.hNums.addFirst(cube.hNums.pollLast());
				sysnc(0);
				break;
			case 2: // 서
				cube.hNums.addLast(cube.hNums.pollFirst());
				sysnc(0);
				break;
			case 3: // 북
				cube.vNums.addLast(cube.vNums.pollFirst());
				sysnc(1);
				break;
			case 4: // 남
				cube.vNums.addFirst(cube.vNums.pollLast());
				sysnc(1);
				break;
			}

			if (map[nx][ny] == 0) { // 주사위 바닥면을 map에 복사
				map[nx][ny] = cube.vNums.get(2);

			} else { // 바닥숫자를 주사위 바닥면에 복사
				botCopy(map[nx][ny]);
				map[nx][ny] = 0;
			}

			System.out.println(cube.vNums.get(0));
		}

	}

	private static void botCopy(int num) {
		cube.vNums.remove(2);
		cube.hNums.remove(2);
		cube.vNums.add(2, num);
		cube.hNums.add(2, num);
	}

	private static void sysnc(int type) {
		if (type == 0) {
			cube.vNums.remove(0);
			cube.vNums.add(0, cube.hNums.get(0));
			cube.vNums.remove(2);
			cube.vNums.add(2, cube.hNums.get(2));
		} else {
			cube.hNums.remove(0);
			cube.hNums.add(0, cube.vNums.get(0));
			cube.hNums.remove(2);
			cube.hNums.add(2, cube.vNums.get(2));
		}
	}

	private static boolean isValid(int nx, int ny) {
		return 0 <= nx && 0 <= ny && nx < N && ny < M;
	}

	static class Node {
		int x;
		int y;
		LinkedList<Integer> vNums; // top down bot up
		LinkedList<Integer> hNums; // top right bot left

		Node(int x, int y) {
			this.x = x;
			this.y = y;
			hNums = new LinkedList<Integer>();
			vNums = new LinkedList<Integer>();
			for (int i = 0; i < 4; i++) {
				hNums.add(0);
				vNums.add(0);
			}
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
