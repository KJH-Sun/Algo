package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_14501_퇴사 {
	static int N, S;
	static Queue<Integer> que = new LinkedList<>();
	static HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
	static boolean[] visit;
	static int[] T;
	static int[] P;
	static int max;

	static void solve(int pos, int total) {

		if (pos == N) {
			if (total > max) {
				max = total;
				return;
			}
			return;
		}

		if (pos + T[pos] <= N) {
			solve(pos + T[pos], total + P[pos]);
		}
		solve(pos + 1, total);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		T = new int[N + 5];
		P = new int[N + 5];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = stoi(st.nextToken());
			P[i] = stoi(st.nextToken());
		}
		solve(0, 0);
		System.out.println(max);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
