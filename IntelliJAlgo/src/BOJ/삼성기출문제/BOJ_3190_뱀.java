package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 뱀 전체의 좌표를 관리하는 Snake 큐 / 머리부분의 좌표만 저장하는 Head노드를 두개 생성
 * 뱀의 머리가 이동할때마다 Head노드를 바꿔서 저장하고, 꼬리의 가장 끝부분을 Que.poll()로 삭제해가면서 진행
 * 사과를 먹은 경우에는 poll을 생략
 * 방향은 3 D 와 같이 방향을 트는 시간과 방향을 나타내는 문자를 쌍으로 주는데, 그것을 관리하기 위해 dir HashMap을 만들어서 관리 
 * move 반복문이 돌때마다 time과 keySet에서 겹치는 부분이 있는지 대조하고, key와 겹친다면 그 문자열이 지시하는 방향대로 direc을 조절한다
 * 이후는 이 direc방향대로 탐색하여, 내 몸에 부딪히거나 나가면 반복문 끝내고 시간 출력
 *
 */
public class BOJ_3190_뱀 {
	static int N, K, L;
	static int[][] field;
	static int time;
	static int[] dx = { 0, 1, 0, -1 }; // 우0하1좌2상3
	static int[] dy = { 1, 0, -1, 0 };
	static int direc;
	static Node head;
	static Queue<Node> snake = new LinkedList<>();
	static StringBuffer sb = new StringBuffer();
	static HashMap<Integer, Character> dir = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = stoi(br.readLine());
		K = stoi(br.readLine());
		field = new int[N][N];
		field[0][0] = 1;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken());
			int y = stoi(st.nextToken());
			field[x - 1][y - 1] = 2;
		}
		L = stoi(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken());
			char c = st.nextToken().charAt(0);
			dir.put(x, c);
		}
		head = new Node(0, 0);
		snake.offer(new Node(0, 0));
		move();
		System.out.println(time);
	}

	private static void move() {
		while (true) {
			time++;
			for (int i : dir.keySet()) { // TreeMap
				if (time - 1 == i) {
					switch (dir.get(i)) {
					case 'D': // 오른쪽
						direc++;
						if (direc >= 4) {
							direc -= 4;
						}
						break;
					case 'L': // 왼쪽
						direc--;
						if (direc < 0) {
							direc += 4;
						}
						break;
					}
				}
			}

			int nx = head.x + dx[direc];
			int ny = head.y + dy[direc];
			if (0 > nx || 0 > ny || nx >= N || ny >= N) {
				return;
			}
			switch (field[nx][ny]) {
			case 0:
				field[nx][ny] = 1;
				head = new Node(nx, ny);
				snake.offer(new Node(nx, ny));
				Node n = snake.poll();
				field[n.x][n.y] = 0;
				break;
			case 1:
				return;
			case 2:
				field[nx][ny] = 1;
				head = new Node(nx, ny);
				snake.offer(new Node(nx, ny));
				break;
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

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
