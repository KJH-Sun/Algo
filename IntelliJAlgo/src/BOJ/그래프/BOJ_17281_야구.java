import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17281_야구 {
	static int N;
	static int[][] data;
	static int max = Integer.MIN_VALUE;
	static int[] order = new int[9];
	static boolean[] visit = new boolean[10];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = stoi(br.readLine());
		data = new int[N+1][10];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				data[i][j] = stoi(st.nextToken());
			}
		}
		order[3] = 1;
		visit[1] = true;
		perm(0, 0); // 순열
		System.out.println(max);

	}

	private static void perm(int cnt, int idx) {
		if (cnt == 8) {
//			System.out.println(Arrays.toString(order));
			max = Math.max(max, play(1, 0));
		}
		if (idx == 3) {
			idx++;
		}

		for (int i = 2; i <= 9; i++) {
			if (visit[i]) {
				continue;
			}
			visit[i] = true;
			order[idx] = i;
			perm(cnt + 1, idx + 1);
			order[idx] = 0;
			visit[i] = false;
		}
	}

	private static int play(int inn, int sIdx) {
		if (inn > N) {
			return 0;
		}
		int sum = 0;
		int outCnt = 0;
		Queue<Integer> que = new LinkedList<>();
		while (outCnt != 3) {
			int n = data[inn][order[sIdx++ % 9]];
			if (n == 0) {
				outCnt++;
				continue;
			}
			int rCnt = que.size();
			for (int i = 0; i < rCnt; i++) {
				int tmp = que.poll();
				if (tmp + n < 4) {
					que.offer(tmp + n);
				} else {
					sum++;
				}
			}
			if (n == 4) {
				sum++;
			} else {
				que.offer(n);
			}
		}

		return sum + play(inn + 1, sIdx % 9);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
