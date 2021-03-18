import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 정올_1863_종교 {
	static int N, M;
	static int[] p, rank;
	static Set<Integer> set = new HashSet<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		p = new int[N + 1];
		rank = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
			rank[i] = 1;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = stoi(st.nextToken());
			int b = stoi(st.nextToken());
			union(a, b);
		}
		for (int i = 1; i <= N; i++) {
			set.add(find(i));
		}
		System.out.println(set.size());
	}


	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) {
			return;
		}
		if (rank[x] >= rank[y]) {
			p[y] = x;
			rank[x] += rank[y];
		} else {
			p[x] = y;
			rank[y] += rank[x];
		}
	}

	public static int find(int x) {
		return p[x] = x == p[x] ? x : find(p[x]);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
