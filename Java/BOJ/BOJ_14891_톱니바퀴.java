import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_14891_톱니바퀴 {
	static int N, K;
	static boolean[] visit;
	static int[][] status;
	static Deque<Character> deque = new ArrayDeque<>();
	static char[][] cog = new char[4][8];
	static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 0; i < 4; i++) {
			cog[i] = br.readLine().toCharArray();
		}
		K = stoi(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			visit = new boolean[4];
			int cNum = stoi(st.nextToken());
			int dir = stoi(st.nextToken());
			chain(cNum - 1, dir);
		}
		System.out.println(ctoi(cog[0][0]) + ctoi(cog[1][0]) * 2 + ctoi(cog[2][0]) * 4 + ctoi(cog[3][0]) * 8);
	}

	private static void chain(int cNum, int dir) {
		visit[cNum] = true;
		for (int i : new int[] { cNum + 1, cNum - 1 }) {
			if (0 <= i && i < 4) {
				if (visit[i]) {
					continue;
				}
				if (cNum > i) {
					if (cog[cNum][6] == cog[i][2]) {
						continue;
					} else {
						chain(i, dir * -1);
					}
				} else {
					if (cog[cNum][2] == cog[i][6]) {
						continue;
					} else {
						chain(i, dir * -1);
					}
				}
			}
		}
		for (int j = 0; j < 8; j++) {
			deque.add(cog[cNum][j]);
		}
		switch (dir) {
		case 1:
			deque.addFirst(deque.pollLast());
			break;
		case -1:
			deque.addLast(deque.poll());
			break;
		}
		for (int i = 0; i < 8; i++) {
			cog[cNum][i] = deque.poll();
		}
	}

	private static int ctoi(char c) {
		return Character.getNumericValue(c);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
