package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 초기 양분을 5로 전부 채운다.
 * 2. 살아있는 나무들의 나이가 어린 순으로 pQ에서 뽑아 자기 자리에서 양분을 뽑도록한다. 실패한다면
 * 죽은 나무들을 넣는 큐에, 성공한다면 나이를 1 올려서 살아있는 나무들의 큐에 넣는다.
 * 3. 이후 죽은 나무들을 양분으로 바꿔 map에 추가한다.
 * 4. 살아있는 나무들의 나이를 확인해 5의 배수라면, 주변에 팔방으로 새로운 나무를 뿌린다.
 * 5. 겨울에는 양분을 추가한다.
 * 2~4를 반복한다.(K번)
 */


public class BOJ_16235_나무재테크 {
	static int N, M, K, map[][], A[][];
	static PriorityQueue<Node> pQ = new PriorityQueue<>();
	static Queue<Node> lT = new ArrayDeque<>();
	static Queue<Node> dT = new ArrayDeque<>();
	static int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		map = new int[N][N];
		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = stoi(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			pQ.add(new Node(stoi(st.nextToken()) - 1, stoi(st.nextToken()) - 1, stoi(st.nextToken())));
		}
		System.out.println(day());
	}

	private static int day() {
		// map 5로 초기화
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 5);
		}
		int year = 0;
		while (true) {
			if (year++ == K) {
				break;
			}
			// 봄
			while (!pQ.isEmpty()) {
				Node n = pQ.poll();
				if (map[n.x][n.y] >= n.age) {
					map[n.x][n.y] -= n.age;
					lT.add(new Node(n.x, n.y, n.age + 1));
				} else {
					dT.add(new Node(n.x, n.y, n.age));
				}
			}

			// 여름
			while (!dT.isEmpty()) {
				Node d = dT.poll();
				map[d.x][d.y] += d.age / 2;
			}
			// 가을
			while (!lT.isEmpty()) {
				Node l = lT.poll();
				pQ.add(l);
				if (l.age % 5 == 0) {
					for (int i = 0; i < 8; i++) {
						int nx = l.x + dx[i];
						int ny = l.y + dy[i];
						if (isValid(nx, ny)) {
							pQ.add(new Node(nx, ny, 1));
						}
					}
				}
			}

			// 겨울
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] += A[i][j];
				}
			}
		}

		return pQ.size();
	}

	static class Node implements Comparable<Node> {
		int x;
		int y;
		int age;

		Node(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Node o) {
			return this.age - o.age;
		}

	}

	private static boolean isValid(int x, int y) {
		return 0 <= x && 0 <= y && x < N && y < N;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
