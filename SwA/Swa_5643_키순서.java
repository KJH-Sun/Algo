import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Swa_5643_키순서 {
	static int T, N, M;
	static List<Integer> big[];
	static List<Integer> small[];
	static boolean visit[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = stoi(br.readLine());
			M = stoi(br.readLine());
			big = new ArrayList[N + 1];
			small = new ArrayList[N + 1];
			visit = new boolean[N + 1];
			for (int i = 1; i <= N; i++) {
				big[i] = new ArrayList<Integer>();
				small[i] = new ArrayList<Integer>();
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				big[s].add(b);
				small[b].add(s);
			}
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				int sum = 0;
				sum += up(i) - 1;
				Arrays.fill(visit, false);
				sum += down(i) - 1;
				if (sum == N - 1) {
					cnt++;
				}
			}
			System.out.println("#" + tc + " " + cnt);
		}
	}

	private static int down(int n) {
		visit[n] = true;
		int sum = 1;
		for (int i : small[n]) {
			if (!visit[i]) {
				sum += down(i);
			}
		}
		return sum;
	}

	private static int up(int n) {
		visit[n] = true;
		int sum = 1;
		for (int i : big[n]) {
			if (!visit[i]) {
				sum += up(i);
			}
		}
		return sum;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
