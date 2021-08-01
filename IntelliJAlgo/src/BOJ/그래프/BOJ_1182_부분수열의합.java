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

public class BOJ_1182_부분수열의합 {
	static int N, S;
	static Queue<Integer> que = new LinkedList<>();
	static HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
	static boolean[] visit;
	static int[] numArr;
	static int sum;
	static int res;
	static Stack<Integer> stack = new Stack<>();

	static void solve(int pos, int cnt, int total) {
		if (pos == N) {
			if (total == S) {
				res++;
				return;
			}
			return;
		}
		solve(pos + 1, cnt + 1, total + numArr[pos]);
		solve(pos + 1, cnt, total);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		S = stoi(st.nextToken());
		numArr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numArr[i] = stoi(st.nextToken());
		}
		solve(0, 0, 0);
		if(S==0&& res>0) {
			System.out.println(res-1);
		}else{
			System.out.println(res);
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
