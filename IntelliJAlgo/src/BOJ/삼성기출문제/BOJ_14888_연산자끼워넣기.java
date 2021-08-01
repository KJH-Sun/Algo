package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888_연산자끼워넣기 {
	static int N;
	static int[] numArr;
	static int[] oper = new int[4];
	static boolean[] visit;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	static void dfs(int depth, int total) {
		if (depth == N) {
			max = Math.max(max, total);
			min = Math.min(min, total);
		}

		for (int i = 0; i < 4; i++) {
			if (oper[i] > 0) {
				oper[i]--;
				switch (i) {
				case 0:
					dfs(depth + 1, total + numArr[depth]);
					break;
				case 1:
					dfs(depth + 1, total - numArr[depth]);
					break;
				case 2:
					dfs(depth + 1, total * numArr[depth]);
					break;
				case 3:
					dfs(depth + 1, total / numArr[depth]);
					break;
				}
				oper[i]++;
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		numArr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numArr[i] = stoi(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			oper[i] = stoi(st.nextToken());
		}
		dfs(1, numArr[0]);
		System.out.println(max);
		System.out.println(min);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
