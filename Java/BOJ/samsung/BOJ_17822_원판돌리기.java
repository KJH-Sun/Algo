package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 입력으로 주어진 회전 방법에 따라서 원판을 회전한다.
 * 2. 원판에서 인접하면서 같은 수를 모두 지운다.
 * 2-1 없는 경우에는, 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
 * 
 * 
 */



public class BOJ_17822_원판돌리기 {
	static int N, M, T;
	static int[][] hanoi;
	static Queue<Order> orders = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		T = stoi(st.nextToken());
		hanoi = new int[N + 1][M];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				hanoi[i][j] = stoi(st.nextToken());
			}
		}
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			orders.add(new Order(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken())));
		}
		System.out.println(solve());

	}

	private static int solve() {
		while (!orders.isEmpty()) {
			Order order = orders.poll();
			int i = 1;
			int xi = order.x;
			while (xi <= N) {
				rotation(xi, order.d, order.k);
				xi = order.x * ++i;
			}
			check();
		}
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if (hanoi[i][j] != -1) {
					sum += hanoi[i][j];
				}
			}
		}
		return sum;
	}

	private static void check() {
		// x 내부에서 같은 수 있는지 확인 (1)
		// y 좌표상 같은 수 있는지 확인 (2)
		boolean[][] same = new boolean[N + 1][M];
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if (hanoi[i][j] == -1) {
					continue;
				}
				int k = (j + 1) % M;
				if (hanoi[i][j] == hanoi[i][k]) {
					same[i][j] = true;
					same[i][k] = true;
				}
				k = (j - 1 + M) % M;
				if (hanoi[i][j] == hanoi[i][k]) {
					same[i][j] = true;
					same[i][k] = true;
				}
				if (i <= N - 1) {
					if (hanoi[i][j] == hanoi[i + 1][j]) {
						same[i][j] = true;
						same[i + 1][j] = true;
					}
				}
			}
		}
		boolean flag = false;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if (same[i][j]) {
					hanoi[i][j] = -1;
					flag = true;
				}
			}
		}

		if (!flag) {
			avgCal();
		}
	}

	private static void avgCal() {
		int sum = 0;
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if (hanoi[i][j] != -1) {
					sum += hanoi[i][j];
					cnt++;
				}
			}
		}
		float avg = sum / (float) cnt;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if (hanoi[i][j] > avg) {
					hanoi[i][j]--;
				} else if (hanoi[i][j] != -1 && hanoi[i][j] < avg) {
					hanoi[i][j]++;
				}
			}
		}
	}

	private static void rotation(int xi, int d, int k) {
		d = d == 0 ? 1 : -1;
		for (int x = 0; x < k; x++) {
			int[] tmp = new int[M];
			for (int i = 0; i < M; i++) {
				tmp[(i + d + M) % M] = hanoi[xi][i];
			}
			hanoi[xi] = tmp;
		}
	}

	static class Order {
		int x;
		int d;
		int k;

		Order(int x, int d, int k) {
			this.x = x;
			this.d = d;
			this.k = k;
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}