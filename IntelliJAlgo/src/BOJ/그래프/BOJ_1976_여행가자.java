import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 유니온파인드
 */

public class BOJ_1976_여행가자 {
	static int N, M;
	static int[] map;
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = stoi(br.readLine());
		M = stoi(br.readLine());
		map = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			map[i] = i;
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				if (stoi(st.nextToken()) == 1) {
					union(i, j);
				}
			}
		}
		if (check(br.readLine())) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}

	// 입력받는 도시들마다 체크해서 시작도시와 root노드값이 같은지 체크, 하나라도 다르면 false
	private static boolean check(String str) {
		StringTokenizer st = new StringTokenizer(str);
		int s = find(stoi(st.nextToken()));
		while (st.hasMoreTokens()) {
			if (s != find(stoi(st.nextToken()))) {
				return false;
			}
		}

		return true;
	}

	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u == v) {
			return;
		}
		map[u] = v;
	}

	private static int find(int u) {
		return map[u] = map[u] == u ? u : find(map[u]);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
